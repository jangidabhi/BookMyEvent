package com.example.capgeminiProject.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.capgeminiProject.entities.Events;
import com.example.capgeminiProject.exceptions.EventNotFoundException;
import com.example.capgeminiProject.repositories.EventRepo;

import jakarta.validation.Valid;

@Service
public class EventServiceImp implements EventService{
	
	private final EventRepo eventRepo;
	
	@Autowired
	public EventServiceImp(EventRepo eventRepo) {
		this.eventRepo = eventRepo;
	}

	@Override
	public List<Events> getAllEvents() {
		// TODO Auto-generated method stub
		return eventRepo.findAll();
	}

	@Override
	public Events getEventById(Long id) {
		// TODO Auto-generated method stub
		return eventRepo.findById(id).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + id));
	}

	@Override
	public Events createEvent(@Valid Events event) {
		// TODO Auto-generated method stub
		return eventRepo.save(event);
	}

	@Override
	public Events updateEvent(Long id,@Valid Events event) {
		// TODO Auto-generated method stub
		Events present=eventRepo.findById(id).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + id));
		present.setTitle(event.getTitle());
		present.setDate(event.getDate());
		present.setTime(event.getTime());
		present.setLocation(event.getLocation());
		present.setOrganizerID(event.getOrganizerID());
		return eventRepo.save(present);
	}

	@Override
	public Events patchEvent(Long id, Events patch) {
		// TODO Auto-generated method stub
		Events present = eventRepo.findById(id)
				.orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + id));

		if (patch.getTitle() != null) {
			present.setTitle(patch.getTitle());
		}
		if(patch.getDate()!=null)
			present.setDate(patch.getDate());
		if(patch.getTime()!=null)
			present.setTime(patch.getTime());
		if(patch.getLocation()!=null)
			present.setLocation(patch.getLocation());
		if(patch.getOrganizerID()!=null)
			present.setOrganizerID(patch.getOrganizerID());
		
		return eventRepo.save(present);
	}

	@Override
	public void deleteEvent(Long id) {
		// TODO Auto-generated method stub
		if (!eventRepo.existsById(id)) {
			throw new EventNotFoundException("Cannot delete. Event not found with ID: " + id);
		}
		eventRepo.deleteById(id);
	}
	
	@Override
	public List<Events> findEventById(Long userId) {
		// TODO Auto-generated method stub
		return eventRepo.findEventById(userId);
	}
	
//	@Override
//	public List<Events> getPastEventsByOrganizer(Long OrganizerId) {
//		// TODO Auto-generated method stub
//		 LocalDate today = LocalDate.now();
//	     return eventRepo.findByOrganizerIDAndDateBefore(OrganizerId, today);
//	}
	@Override
	public List<Events> getPastEventsByOrganizer(Long OrganizerId) {
	    LocalDate today = LocalDate.now();
	    return eventRepo.findByOrganizerIDAndDateBefore(OrganizerId, today);
	}
	
	@Override
	public List<Events> getPastEventsForUser() {
		// TODO Auto-generated method stub
		LocalDate today = LocalDate.now();
	    return eventRepo.findByDateBefore(today);
	}
	
	@Override
	public List<Events> getUpcommingEventsForUser() {
		// TODO Auto-generated method stub
		LocalDate today=LocalDate.now();
		return eventRepo.findByDateAfter(today);
	}
	
	@Override
	public List<Events> findByDate() {
		// TODO Auto-generated method stub
		LocalDate today=LocalDate.now();
		return eventRepo.findByDate(today);
	}
	
	@Override
	public Long countDateAfter() {
		LocalDate today = LocalDate.now();
		return eventRepo.countDateAfter(today);
	}
}
