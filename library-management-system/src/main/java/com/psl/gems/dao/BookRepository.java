package com.psl.gems.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.psl.gems.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	
	List<Book> findByTitleContainingIgnoreCase(String title);
	List<Book> findByAuthorContainingIgnoreCase(String author);
	List<Book> findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(String title, String author);
}
