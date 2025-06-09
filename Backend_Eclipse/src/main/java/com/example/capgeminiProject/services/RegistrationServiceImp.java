package com.example.capgeminiProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.capgeminiProject.entities.Registrations;
import com.example.capgeminiProject.exceptions.RegistrationNotFoundException;
import com.example.capgeminiProject.repositories.RegistrationRepo;

import jakarta.validation.Valid;

@Service
public class RegistrationServiceImp implements RegistrationService{
	private final RegistrationRepo registrationRepo;

	@Autowired
	public RegistrationServiceImp(RegistrationRepo registrationRepo) {
		this.registrationRepo = registrationRepo;
	}

	@Override
	public List<Registrations> getAllRegistrations() {
		// TODO Auto-generated method stub
		return registrationRepo.findAll();
	}

	@Override
	public Registrations getRegistrationById(Long id) {
		// TODO Auto-generated method stub
		return registrationRepo.findById(id).orElseThrow(() -> new RegistrationNotFoundException("Registration not found with ID: " + id));
	}

	@Override
	public Registrations createRegistration(@Valid Registrations registration) {
		// TODO Auto-generated method stub
		return registrationRepo.save(registration);
	}

	@Override
	public Registrations updateRegistration(Long id, @Valid Registrations registration) {
		// TODO Auto-generated method stub
		Registrations present=registrationRepo.findById(id).orElseThrow(() -> new RegistrationNotFoundException("Registration not found with ID: " + id));;
		present.setEventID(registration.getEventID());
		present.setUserID(registration.getUserID());
		present.setRegistrationDate(registration.getRegistrationDate());
		
		return null;
	}

	@Override
	public Registrations patchRegistration(Long id, Registrations patch) {
		// TODO Auto-generated method stub
		Registrations present = registrationRepo.findById(id)
				.orElseThrow(() -> new RegistrationNotFoundException("Registration not found with ID: " + id));

		if(patch.getEventID()!=null)
			present.setEventID(patch.getEventID());
		if(patch.getUserID()!=null)
			present.setUserID(patch.getUserID());
		if(patch.getRegistrationDate()!=null)
			present.setRegistrationDate(patch.getRegistrationDate());
		
		return null;
	}

	@Override
	public void deleteRegistration(Long id) {
		// TODO Auto-generated method stub
		if (!registrationRepo.existsById(id)) {
			throw new RegistrationNotFoundException("Cannot delete. Registration not found with ID: " + id);
		}
		registrationRepo.deleteById(id);
	}
	
	@Override
	public Long countRegistration(Long eventId) {
		// TODO Auto-generated method stub
		return registrationRepo.countRegistration(eventId);
	}
}
