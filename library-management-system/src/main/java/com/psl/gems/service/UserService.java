package com.psl.gems.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.gems.dao.UserRepository;
import com.psl.gems.model.User;

@Service
public class UserService 
{
	@Autowired
	UserRepository usRepo;
	
	public User save(User user) {
		usRepo.save(user);
		return user;
	}
	
	public void saveById(int userId) {
		User user = usRepo.findById(userId).get();
		usRepo.save(user);
	}
	
	public User findById(int id) {
		User user = usRepo.findById(id).get();
		return user;
	}
	
	public List<User> findAll(){
		return (List<User>) usRepo.findAll();
	}
	
	public List<User> getByName(String name){
		List<User> users = new ArrayList<User>();
		users = usRepo.findByNameContainingIgnoreCase(name);
		return users;
	}
	
	public User getByUsername(String username) {
		User user= usRepo.findByUsername(username).get();
		return user;
	}
	

}
