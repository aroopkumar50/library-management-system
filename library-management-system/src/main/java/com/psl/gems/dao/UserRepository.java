package com.psl.gems.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.psl.gems.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	List<User> findByName(String name);

	Optional<User> findByUsername(String username);
	
	

}
