package com.example.capgeminiProject.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Registrations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RegistrationID")
    @JsonProperty("RegistrationID")
	private Long RegistrationID;
	
	@NotNull(message = "Suer ID must be provided")
	@Column(name = "UserID")
    @JsonProperty("UserID")
	private Long UserID;
	
	@NotNull(message = "Event ID must be provided")
	@Column(name = "EventID")
    @JsonProperty("EventID")
	private Long EventID;
	
	@NotNull(message="Date is mandatory")
	@Column(name = "RegistrationDate")
    @JsonProperty("RegistrationDate")
	private LocalDate RegistrationDate;

	public Registrations() {
		super();
	}

	public Registrations(Long registrationID, @NotNull(message = "Suer ID must be provided") Long userID,
			@NotNull(message = "Event ID must be provided") Long eventID,
			@NotBlank(message = "Date is mandatory") LocalDate registrationDate) {
		super();
		RegistrationID = registrationID;
		UserID = userID;
		EventID = eventID;
		RegistrationDate = registrationDate;
	}

	public Long getRegistrationID() {
		return RegistrationID;
	}

	public void setRegistrationID(Long registrationID) {
		RegistrationID = registrationID;
	}

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public Long getEventID() {
		return EventID;
	}

	public void setEventID(Long eventID) {
		EventID = eventID;
	}

	public LocalDate getRegistrationDate() {
		return RegistrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		RegistrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "Registrations [RegistrationID=" + RegistrationID + ", UserID=" + UserID + ", EventID=" + EventID
				+ ", RegistrationDate=" + RegistrationDate + "]";
	}
}
