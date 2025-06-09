package com.example.capgeminiProject.security;

import com.example.capgeminiProject.entities.Users;
import com.example.capgeminiProject.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch the user by email
        Users user = usersRepository.findByEmail(email);

        // If no user found, throw an exception
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Set up user roles/authorities (can be expanded based on user roles in your system)
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserType())); // Assuming userType is the role

        // Return a Spring Security user with email, password, and authorities
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
