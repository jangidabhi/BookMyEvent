package com.example.capgeminiProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.example.capgeminiProject.entities.Registrations;

public interface RegistrationService{
	List<Registrations> getAllRegistrations();
	
	Registrations getRegistrationById(Long id);
	
	Registrations createRegistration(Registrations registration);
	
	Registrations updateRegistration(Long id,Registrations registration);
	
	Registrations patchRegistration(Long id,Registrations registration);
	
	void deleteRegistration(Long id);
	
	Long countRegistration(Long eventId);
}
