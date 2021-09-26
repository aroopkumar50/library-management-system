package com.psl.gems;

import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.psl.gems.dao.BookRepository;
import com.psl.gems.model.Book;

@EnableJpaRepositories(basePackages="com.psl.gems")
@DataJpaTest
class BookRepositoryTest {
	
	@Autowired
	private BookRepository bookRepo;
	
	
	
	@Test
	void testCreateBook() {
		Book book = new Book();
		book.setAuthor("Ruskin Bond");
		book.setISBN(123456789);
		book.setTitle("Bedtime Stories");
		
		Book b1 = bookRepo.save(book);
		
		assertNotNull(b1);
		assertTrue(b1.getISBN()>0);
	}

}
