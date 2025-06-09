package com.example.capgeminiProject.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.capgeminiProject.entities.Registrations;
import com.example.capgeminiProject.services.RegistrationService;
import com.example.capgeminiProject.services.UsersService;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {
	private final RegistrationService service;
//	private  UsersService usersService;

	@Autowired
	public RegistrationController(RegistrationService registrationService) {
		this.service = registrationService; 
	}
	
	@GetMapping
	public ResponseEntity<List<Registrations>> getAllRegistartions() {
		List<Registrations> registrations = service.getAllRegistrations();
		return ResponseEntity.status(HttpStatus.OK).body(registrations);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Registrations> getRegistration(@PathVariable Long id) {
		Registrations registration = service.getRegistrationById(id);
		return ResponseEntity.status(HttpStatus.OK).body(registration);
	}
	
	@PostMapping
	public ResponseEntity<Registrations> createRegistartion(@RequestBody Registrations registration) {
		Registrations saved = service.createRegistration(registration);
		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/registrations/" + saved.getRegistrationID()))
				.body(saved);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Registrations> updateRegistration(@PathVariable Long id, @RequestBody Registrations newRegistration) {
		Registrations updated = service.updateRegistration(id, newRegistration);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Registrations> patchEmployee(@PathVariable Long id, @RequestBody Registrations patch) {
		Registrations updated = service.patchRegistration(id, patch);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		service.deleteRegistration(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/count/{eventId}")
	public ResponseEntity<Long> countRegistrations(@PathVariable Long eventId){
		return ResponseEntity.status(HttpStatus.OK).body(service.countRegistration(eventId));
	}
}
