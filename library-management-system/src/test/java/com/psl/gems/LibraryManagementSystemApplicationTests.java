package com.psl.gems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.psl.gems.controller.EmployeeController;
import com.psl.gems.dao.BookRepository;
import com.psl.gems.dao.UserRepository;
import com.psl.gems.model.Book;
import com.psl.gems.model.User;
import com.psl.gems.service.BookService;
import com.psl.gems.service.UserService;


@SpringBootTest
class LibraryManagementSystemApplicationTests {

	@Autowired
	BCryptPasswordEncoder pwEncoder;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private BookRepository bookRepo;
	
	@MockBean
	private UserRepository userRepo;
	
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
		
		
		when(bookRepo.findAll()).thenReturn(Stream.of(book1,book2).collect(Collectors.toList()));
		assertEquals(2, bookService.findAll().size());
	
	}
	
	@Test
	public void getBookbyAuthorTest() {
		
		
		String author="Enid Blyton";
		Book book3=new Book();
		book3.setISBN(986702);
		book3.setAuthor(author);
		book3.setTitle("Famous five");
		when(bookRepo.findByAuthorContainingIgnoreCase(author)).thenReturn(Stream.of(book3).collect(Collectors.toList()));
		
		assertEquals(1,bookService.getByAuthor(author).size());
		
	}
	
	@Test
	public void getBookbyIDTest() {
		
		String author="Enid Blyton";
		long ISBN = 986702L;
		Book book3=new Book();
		book3.setISBN(ISBN);
		book3.setAuthor(author);
		book3.setTitle("Famous five");
		when(bookRepo.findById(ISBN)).thenReturn(Optional.of(book3));
		
		assertEquals(book3,bookService.findById(ISBN));
		
	} 
	
	@Test
	public void getbyTitleTest() {
		
		String author="Enid Blyton";
		Book book3=new Book();
		book3.setISBN(986702);
		book3.setAuthor(author);
		book3.setTitle("Famous five");
		when(bookRepo.findByTitleContainingIgnoreCase("Fam")).thenReturn(Stream.of(book3).collect(Collectors.toList()));
		
		assertEquals(1,bookService.getByTitle("Fam").size());
	}
	
	@Test
	public void getbyTitleAndAuthorTest() {
		
		String author="Enid Blyton";
		Book book3=new Book();
		book3.setISBN(986702);
		book3.setAuthor(author);
		book3.setTitle("Famous five");
		when(bookRepo.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase("Fam","Enid")).thenReturn(Stream.of(book3).collect(Collectors.toList()));
		
		assertEquals(1,bookService.getByTitleAndAuthor("Fam","Enid").size());
	}
	
	@Test
	public void searchBooksTest() {
		
		String author="Enid Blyton";
		Book book3=new Book();
		book3.setISBN(986702);
		book3.setAuthor(author);
		book3.setTitle("Famous five");
		when(bookRepo.findByTitleContainingIgnoreCase("Fam")).thenReturn(Stream.of(book3).collect(Collectors.toList()));
		when(bookRepo.findByAuthorContainingIgnoreCase("Enid")).thenReturn(Stream.of(book3).collect(Collectors.toList()));
		when(bookRepo.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase("Fam","Enid")).thenReturn(Stream.of(book3).collect(Collectors.toList()));
		
		assertEquals(1,bookService.searchBooks("Fam","Enid").size());
		assertEquals(1,bookService.searchBooks("Fam",null).size());
		assertEquals(1,bookService.searchBooks(null,"Enid").size());
		assertEquals(0,bookService.searchBooks(null,null).size());
	}
	
	@Test
	public void deleteBookTest() {
		
		Book book5=new Book();
		book5.setISBN(986703L);
		book5.setAuthor("Jeff Bezos");
		book5.setTitle("Amazon");
		bookService.deleteById(986703L);
		verify(bookRepo,times(1)).deleteById(986703L);
		
	}

	@Test
	public void saveBookTest() {
		
		String title="Diary of a whimpy kid";
		Book book1=new Book();
		book1.setISBN(986704);
		book1.setAuthor("Jeff Kinney");
		book1.setTitle(title);
		
		when(bookRepo.save(book1)).thenReturn(book1);
		assertEquals(book1, bookService.save(book1));
	}
	
	
	
	@Test
	public void saveUserTest() {
		
		User user = new User();
		user.setName("Abhinav");
		user.setUsername("abhi");
		user.setPassword(pwEncoder.encode("test"));
		user.setRole("member");
		
		when(userRepo.save(user)).thenReturn(user);
		assertEquals(user, userService.save(user));
	}
	
	@Test
	public void findbyIDUsersTest()
	{
		User user1 = new User();
		user1.setName("Pratik");
		user1.setUsername("prats");
		user1.setPassword(pwEncoder.encode("test"));
		user1.setRole("member");
		
		when(userRepo.findById(1)).thenReturn(Optional.of(user1));
		assertEquals(user1, userService.findById(1));
	}
	
	@Test
	public void findAllUsersTest()
	{
		User user1 = new User();
		user1.setName("Pratik");
		user1.setUsername("prats");
		user1.setPassword(pwEncoder.encode("test"));
		user1.setRole("member");
		
		User user2 = new User();
		user2.setName("Niraj");
		user2.setUsername("Niraj");
		user2.setPassword(pwEncoder.encode("test"));
		user2.setRole("member");
		
		when(userRepo.findAll()).thenReturn(Stream.of(user1,user2).collect(Collectors.toList()));
		assertEquals(2, userService.findAll().size());
	}
	
	@Test
	public void getUserbyNameTest() {
		
		String name="xyz";
		User user1 = new User();
		user1.setName("xyz");
		user1.setUsername("prats");
		user1.setPassword(pwEncoder.encode("test"));
		user1.setRole("member");
		user1.setEnabled(true);
		when(userRepo.findByNameContainingIgnoreCase(name)).thenReturn(Stream.of(user1).collect(Collectors.toList()));
		
		assertEquals(1,userService.getByName("xyz").size());
		
	}
}
