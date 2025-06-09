package com.example.capgeminiProject.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Events {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EventID")
//    @JsonProperty("EventID")
	private Long EventID;
	
	@NotBlank(message="Title is mandatory")
	@Column(name = "Title")
//    @JsonProperty("Title")
	private String Title;
	
	@NotNull(message="Date is mandatory")
	@Column(name = "Date")
//    @JsonProperty("Date")
	private LocalDate Date;
	
	@Column(name = "Time",columnDefinition = "TIME")
//    @JsonProperty("Time")
    private LocalTime Time; 
	
	@NotNull
	@Column(name = "OrganizerID")
//    @JsonProperty("OrganizerID")
	private Long OrganizerID;
	
	@NotBlank(message="Location is mandatory")
//    @JsonProperty("Location")
	@Column(name = "Location")
	private String Location;

	public Events() {
		super();
	}

	public Events(Long eventID, @NotBlank(message = "Title is mandatory") String title,
			@NotBlank(message = "Date is mandatory") LocalDate date, LocalTime time, @NotNull Long organizerID,
			@NotBlank(message = "Location is mandatory") String location) {
		super();
		EventID = eventID;
		Title = title;
		Date = date;
		Time = time;
		OrganizerID = organizerID;
		Location = location;
	}

    @JsonProperty("EventID")
	public Long getEventID() {
		return EventID;
	}

	public void setEventID(Long eventID) {
		EventID = eventID;
	}

    @JsonProperty("Title")
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

    @JsonProperty("Date")
	public LocalDate getDate() {
		return Date;
	}

	public void setDate(LocalDate date) {
		Date = date;
	}

    @JsonProperty("Time")
	public LocalTime getTime() {
		return Time;
	}

	public void setTime(LocalTime time) {
		Time = time;
	}

    @JsonProperty("OrganizerID")
	public Long getOrganizerID() {
		return OrganizerID;
	}

	public void setOrganizerID(Long organizerID) {
		OrganizerID = organizerID;
	}

    @JsonProperty("Location")
	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	@Override
	public String toString() {
		return "Events [EventID=" + EventID + ", Title=" + Title + ", Date=" + Date + ", Time=" + Time
				+ ", OrganizerID=" + OrganizerID + ", Location=" + Location + "]";
	}
}
