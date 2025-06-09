package com.example.capgeminiProject.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.capgeminiProject.dto.EmailExists;
import com.example.capgeminiProject.dto.LoginRequest;
import com.example.capgeminiProject.entities.Users;
import com.example.capgeminiProject.repositories.UsersRepo;
import com.example.capgeminiProject.services.UsersService;

@CrossOrigin(origins = "http://127.0.0.1:5500")  // <--- ADD THIS
@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UsersService service;
	private final UsersRepo usersRepo;

	public UserController(UsersService service ,UsersRepo usersRepo) {
		super();
		this.service = service;
		this.usersRepo=usersRepo;
	}
	
	@GetMapping
	public ResponseEntity<List<Users>> getAllUsers(){
		List<Users> user=service.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Users> getUser(@PathVariable Long id){
		Users user=service.getUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@PostMapping
	public ResponseEntity<Users> createUser(@RequestBody Users user){
		Users saved=service.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/users/" + saved.getUserID())).body(saved);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable Long id,@RequestBody Users newUser){
		Users user=service.updateUser(id, newUser);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Users> patchUser(@PathVariable Long id, @RequestBody Users patch){
		Users updated=service.patchUser(id, patch);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		service.deleteUser(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<Users> login(@RequestBody LoginRequest request){
		Users user=usersRepo.findByEmailAndPassword(request.getEmail(), request.getPassword());
		
		if(user!=null)
			return ResponseEntity.ok(user);
		else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@GetMapping("/exists")
	public ResponseEntity<Boolean> findByEmail(@RequestParam String email){
		Boolean present=usersRepo.findByEmail(email)!=null;
		return ResponseEntity.ok(present);
		
	}
}
