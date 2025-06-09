package com.example.capgeminiProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.capgeminiProject.entities.Users;
import com.example.capgeminiProject.exceptions.UserNotFoundException;
import com.example.capgeminiProject.repositories.UsersRepo;

import jakarta.validation.Valid;

@Service
public class UsersServiceImp implements UsersService {
	private final UsersRepo usersRepo;

	@Autowired
	public UsersServiceImp(UsersRepo usersRepo) {
		this.usersRepo = usersRepo;
	}

	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return usersRepo.findAll();
	}

	@Override
	public Users getUserById(Long id) {
		// TODO Auto-generated method stub
		return usersRepo.findById(id).orElseThrow(()->new UserNotFoundException("User not found with ID: " + id));
	}

	@Override
	public Users createUser(Users user) {
		// TODO Auto-generated method stub
		return usersRepo.save(user);
	}

	@Override
	public Users updateUser(Long id,@Valid Users user) {
		// TODO Auto-generated method stub
		Users present=usersRepo.findById(id).orElseThrow(()->new UserNotFoundException("User not found with ID: " + id));
		present.setName(user.getName());
		present.setEmail(user.getEmail());
		present.setPassword(user.getPassword());
		present.setPhone(user.getPhone());
		present.setUserType(user.getUserType());
		
		return usersRepo.save(present);
	}

	@Override
	public Users patchUser(Long id, Users user) {
		// TODO Auto-generated method stub
		Users present=usersRepo.findById(id).orElseThrow(()->new UserNotFoundException("User not found with ID: " + id));
		
		if(user.getName()!=null)
			present.setName(user.getName());
		if(user.getEmail()!=null)
			present.setEmail(user.getEmail());
		if(user.getPassword()!=null)
			present.setPassword(user.getPassword());
		if(user.getPhone()!=null)
			present.setPhone(user.getPhone());
		if(user.getUserType()!=null)
			present.setUserType(user.getUserType());
		return usersRepo.save(present);
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		if(!usersRepo.existsById(id))
			throw new UserNotFoundException("Cannot delete. User not found with ID: " + id);
		
		usersRepo.deleteById(id);
	}
	
	@Override
	public Users findByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return usersRepo.findByEmailAndPassword(email, password);
	}
	
	@Override
	public Users findByEmail(String email) {
		// TODO Auto-generated method stub
		return usersRepo.findByEmail(email);
	}
}
