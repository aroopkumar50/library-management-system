package com.psl.gems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.gems.dao.BookObjRepository;
import com.psl.gems.dao.IssueRepository;
import com.psl.gems.model.Book;
import com.psl.gems.model.BookObj;
import com.psl.gems.model.Issue;
import com.psl.gems.model.IssueStatus;
import com.psl.gems.model.User;

@Service
public class IssueService {
	@Autowired
	IssueRepository issueRepository;
	
	@Autowired
	BookObjRepository bookObjRepository;
	
	public List<Issue> findAll() {
		return (List<Issue>) issueRepository.findAll();
	}
	
	public Issue findById(int id) {
		return issueRepository.findById(id).get();
	}
	
	public Issue save(Issue issue) {
		return issueRepository.save(issue);
	}
	
	public List<Issue> getReservations() {
		return issueRepository.findByStatus(IssueStatus.RESERVATION);
	}
	
	public List<Issue> getIssues() {
		return issueRepository.findByStatus(IssueStatus.ISSUED);
	}
	
	public List<Issue> getOldIssues() {
		return issueRepository.findByStatus(IssueStatus.RETURNED);
	}
	
	public void issueBook(Book book, User user) {
		BookObj bookObj = bookObjRepository.findFirstAvailableCopyByBookId(book.getISBN());
		Issue issue = new Issue();
		issue.setBookObj(bookObj);
		issue.setUsers(user);
		issue.setStatus(IssueStatus.ISSUED);
		issueRepository.save(issue);
	}
}
