package com.example.capgeminiProject.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.capgeminiProject.entities.Events;

public interface EventRepo extends JpaRepository<Events, Long>{

	@Query("select e from Events e where e.OrganizerID= :userId")
	List<Events> findEventById(@Param("userId") Long userId);
	
	// For past events for organizer
	@Query("SELECT e FROM Events e WHERE e.OrganizerID = :OrganizerId AND e.Date < :date")
	List<Events> findByOrganizerIDAndDateBefore(@Param("OrganizerId") Long OrganizerID, @Param("date") LocalDate date);

	//For all past events for user
	@Query("SELECT e FROM Events e WHERE e.Date < :date")
	List<Events> findByDateBefore(@Param("date") LocalDate date);
	
	//For all upcoming events for user
	@Query("select e from Events e where e.Date> :date")
	List<Events> findByDateAfter(@Param("date") LocalDate date);

	@Query("select e from Events e where e.Date= :date")
	List<Events> findByDate(@Param("date") LocalDate date);
	
	@Query("select count(*) from Events e where e.Date > :date")
	Long countDateAfter(@Param("date") LocalDate date);
}
