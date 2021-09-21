package com.psl.gems.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.psl.gems.model.Issue;
import com.psl.gems.model.IssueStatus;

public interface IssueRepository extends CrudRepository<Issue, Integer> {
	
	List<Issue> findByStatus(IssueStatus status);

}
