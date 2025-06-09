package com.example.capgeminiProject.controllers;

import java.util.HashMap;
import com.example.capgeminiProject.dto.LoginRequest;
import com.example.capgeminiProject.dto.ResponseToken;
import com.example.capgeminiProject.entities.Users;
import com.example.capgeminiProject.repositories.UsersRepo;
import com.example.capgeminiProject.security.JwtUtil;
import com.example.capgeminiProject.security.MyUserDetailsService;
import com.example.capgeminiProject.services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private MyUserDetailsService userDetailsService;
    
    @Autowired
    UsersRepo usersRepo;
    
    @Autowired
    UsersService usersService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication.getName());

//        return ResponseEntity.ok(Map.of("token", jwt));
        return ResponseEntity.ok(new ResponseToken(jwt));

    }
    
    
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
//        );
//
//        if (authentication.isAuthenticated()) {
//            // Assuming you have a method to find the user by email or username
//            Users user = usersService.findByEmail(loginRequest.getEmail());
//
//            Map<String, Object> claims = new HashMap<>();
//            claims.put("email", user.getEmail());
//            claims.put("userid", user.getUserID());
//            claims.put("usertype", user.getUserType());
//
//            String jwt = jwtUtil.generateToken(user.getEmail(), claims);
//            ResponseToken responseToken = new ResponseToken(jwt);
//            return ResponseEntity.status(HttpStatus.OK).body(responseToken);
//        }
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not Authorized !!");
//    }

    
    
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody Users user) {
//        // Encode the password before saving
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        usersRepo.save(user);
//        return ResponseEntity.ok("User registered successfully");
//    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user) {
        // Set default value for UserType if not provided
        if (user.getUserType() == null || user.getUserType().isEmpty()) {
            user.setUserType("Normal User"); // Default value
        }

        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepo.save(user);
        return ResponseEntity.ok("User registered successfully");
    }


}
