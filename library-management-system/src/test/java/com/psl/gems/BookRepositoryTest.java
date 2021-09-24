package com.psl.gems;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.psl.gems.dao.BookRepository;
import com.psl.gems.model.Book;

@DataJpaTest
class BookRepositoryTest {
	
	@Autowired
	BCryptPasswordEncoder pwEncoder;
	
	@Autowired
	private BookRepository undertest;

	@AfterEach
	void tearDown() throws Exception {
		
		undertest.deleteAll();
		
	}

	@Test
	void itShouldCheckWhenBookTitleExists() {
		
		//given 
		String title="Diary of a whimpy kid";
		Book book=new Book();
		book.setISBN(986704);
		book.setAuthor("Jeff Kinney");
		book.setTitle(title);
		
		undertest.save(book);
		
		//when
		boolean expected=undertest.selectExistsTitle(title);
		
		//then
		assertThat(expected).isTrue();
		
	}

}
