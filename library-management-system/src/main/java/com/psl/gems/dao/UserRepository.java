package com.psl.gems.dao;

import org.springframework.data.repository.CrudRepository;

import com.psl.gems.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
