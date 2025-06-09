package com.example.capgeminiProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.capgeminiProject.entities.Registrations;

public interface RegistrationRepo extends JpaRepository<Registrations, Long>{

	@Query("SELECT COUNT(*) FROM Registrations r WHERE r.EventID = :eventId")
	Long countRegistration(@Param("eventId") Long eventId);

}
