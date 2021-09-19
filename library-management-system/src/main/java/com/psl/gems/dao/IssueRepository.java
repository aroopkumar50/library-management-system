package com.psl.gems.dao;

import org.springframework.data.repository.CrudRepository;

import com.psl.gems.model.Issue;

public interface IssueRepository extends CrudRepository<Issue, Integer> {
	
	

}
