package com.psl.gems.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.psl.gems.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	
	List<Book> findByTitleContainingIgnoreCase(String title);
	List<Book> findByAuthorContainingIgnoreCase(String author);
	List<Book> findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(String title, String author);
	
	@Query(value="SELECT EXISTS(select * from book_obj b LEFT OUTER JOIN issue i ON b.id = i.book_obj_id WHERE b.book_isbn = ?1 AND (i.status IS NULL OR (i.status != 2 AND i.status != 0)))", nativeQuery=true)
	int checkAvailabilityById(long id);
	
}
