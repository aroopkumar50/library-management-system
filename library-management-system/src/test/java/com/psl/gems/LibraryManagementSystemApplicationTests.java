package com.psl.gems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.psl.gems.dao.BookRepository;
import com.psl.gems.model.Book;
import com.psl.gems.service.BookService;


@SpringBootTest
class LibraryManagementSystemApplicationTests {

	@Autowired
	private BookService service;
	
	@MockBean
	private BookRepository repository;
	
	
	@Test
	public void getBooksTest() {
		
		String title="Diary of a whimpy kid";
		Book book1=new Book();
		book1.setISBN(986704);
		book1.setAuthor("Jeff Kinney");
		book1.setTitle(title);
		
		Book book2=new Book();
		book2.setISBN(986703);
		book2.setAuthor("Jeff Bezos");
		book2.setTitle("Amazon");
		
		
		when(repository.findAll()).thenReturn(Stream.of(book1,book2).collect(Collectors.toList()));
		assertEquals(2, service.findAll().size());
	
	}
	
	@Test
	public void getBookbyAuthor() {
		
		
		String author="Enid Blyton";
		Book book3=new Book();
		book3.setISBN(986702);
		book3.setAuthor(author);
		book3.setTitle("Famous five");
		when(repository.findByAuthorContainingIgnoreCase(author)).thenReturn(Stream.of(book3).collect(Collectors.toList()));
		
		assertEquals(1,service.getByAuthor(author).size());
		
	}
	
	@Test
	public void deleteBookTest() {
		
		Book book5=new Book();
		book5.setISBN(986703L);
		book5.setAuthor("Jeff Bezos");
		book5.setTitle("Amazon");
		service.deleteById(986703L);
		verify(repository,times(1)).deleteById(986703L);
		
	}

	

}
