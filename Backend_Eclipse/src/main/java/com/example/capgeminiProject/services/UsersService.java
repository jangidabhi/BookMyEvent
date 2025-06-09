package com.example.capgeminiProject.services;

import java.util.List;

import com.example.capgeminiProject.entities.Users;

public interface UsersService {
	List<Users> getAllUsers();
	
	Users getUserById(Long id);
	
	Users createUser(Users user);
	
	Users updateUser(Long id,Users user);
	
	Users patchUser(Long id,Users user);
	
	void deleteUser(Long id);
	
	Users findByEmailAndPassword(String email,String password);
	
	Users findByEmail(String email);
	
	
}
