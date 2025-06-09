package com.example.capgeminiProject.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.capgeminiProject.entities.Events;

public interface EventService {
	List<Events> getAllEvents();
	
	Events getEventById(Long id);
	
	Events createEvent(Events event);
	
	Events updateEvent(Long id,Events event);
	
	Events patchEvent(Long id,Events event);
	
	void deleteEvent(Long id);
	
	List<Events> findEventById(Long userId);
	
	// For past events for organizer
    List<Events> getPastEventsByOrganizer(Long OrganizerId);
    
    //For past events for user
	List<Events> getPastEventsForUser();

	//For upcoming events for user
	List<Events> getUpcommingEventsForUser();
	
	List<Events> findByDate();
	
	Long countDateAfter();

}
