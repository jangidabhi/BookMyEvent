package com.example.capgeminiProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.capgeminiProject.entities.Users;

public interface UsersRepo extends JpaRepository<Users, Long>{

	@Query("select u from Users u where u.Email=:email AND u.Password=:password")
	Users findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
	
	@Query("select u from Users u where u.Email= :email")
	Users findByEmail(@Param("email") String email);
	
}
