package com.example.capgeminiProject.controllers;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.capgeminiProject.entities.Events;
import com.example.capgeminiProject.exceptions.NoPastEventException;
import com.example.capgeminiProject.services.EventService;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/events")
public class EventController {
	private final EventService service;

	public EventController(EventService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<Events>> getAllEvents(){
		List<Events> event=service.getAllEvents();
		return ResponseEntity.status(HttpStatus.OK).body(event);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Events> getEvent(@PathVariable Long id) {
		Events event = service.getEventById(id);
		return ResponseEntity.status(HttpStatus.OK).body(event);
	}
	
	@PostMapping
	public ResponseEntity<Events> createEvent(@RequestBody Events event) {
		Events saved = service.createEvent(event);
		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/events/" + saved.getEventID()))
				.body(saved);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Events> updateEvent(@PathVariable Long id, @RequestBody Events newEvent) {
		Events updated = service.updateEvent(id, newEvent);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Events> patchEvent(@PathVariable Long id, @RequestBody Events patch) {
		Events updated = service.patchEvent(id, patch);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
		service.deleteEvent(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/list/{id}")
	public ResponseEntity<List<Events>> findEventById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(service.findEventById(id));
	}
	
//	@GetMapping("/past")
//	public List<Events> getPastEventsByOrganizer(@RequestParam Long organizerId) {
//	    return service.getPastEventsByOrganizer(organizerId);
//	}
	
	@GetMapping("/past")
	public ResponseEntity<List<Events>> getPastEventsByOrganizer(@RequestParam Long organizerId) {
	    List<Events> events = service.getPastEventsByOrganizer(organizerId);
	    if (events.isEmpty()) {
	        throw new NoPastEventException("No past evet to display");
	    }
//	    return ResponseEntity.ok(events);
		return ResponseEntity.status(HttpStatus.OK).body(events);
	}

	@GetMapping("/user/past")
	public ResponseEntity<List<Events>> getPastEventsForUser(){
		List<Events> events=service.getPastEventsForUser();
		if (events.isEmpty()) {
	        throw new NoPastEventException("No past evet to display");
	    }
		return ResponseEntity.status(HttpStatus.OK).body(events);
	}

	@GetMapping("/user/upcomming")
	public ResponseEntity<List<Events>> getUpcommingEventsForUser(){
		List<Events> events=service.getUpcommingEventsForUser();
		if(events.isEmpty())
			throw new NoPastEventException("No upcomming events to display");
		return ResponseEntity.status(HttpStatus.OK).body(events);
	}
	
	@GetMapping("/organizer/ongoining")
	public ResponseEntity<List<Events>> findByDate(){
		List<Events> events=service.findByDate();
		if(events.isEmpty())
			throw new NoPastEventException("No upcomming events to display");
		return ResponseEntity.status(HttpStatus.OK).body(events);
	}
	
	@GetMapping("/count")
	public ResponseEntity<Long> countDateAfter(){
		return ResponseEntity.status(HttpStatus.OK).body(service.countDateAfter());
	}
}
