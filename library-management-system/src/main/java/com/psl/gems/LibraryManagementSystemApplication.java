package com.psl.gems;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.psl.gems.dao.BookObjRepository;
import com.psl.gems.dao.BookRepository;
import com.psl.gems.dao.UserRepository;
import com.psl.gems.model.Book;
import com.psl.gems.model.BookObj;
import com.psl.gems.model.User;

@SpringBootApplication
public class LibraryManagementSystemApplication {
	
	@Autowired
	BCryptPasswordEncoder pwEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookObjRepository bookObjRepository;

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}
	
	@Bean
	@Transactional
	InitializingBean initialCode() {
		return () -> {
			long count = userRepository.count();
			if (count == 0L) {
				User user = new User();
				user.setName("Admin");
				user.setUsername("admin");
				user.setPassword(pwEncoder.encode("test"));
				user.setRole("librarian");
				userRepository.save(user);
			}
			
//			Book book = new Book();
//			book.setISBN(1);
//			BookObj bookObj = new BookObj();
//			Set<BookObj> bookObjSet = new HashSet<BookObj>();
//			book.setBookObjs(bookObjSet);
//			book = bookRepository.save(book);
////			bookRepository.delete(book);
//			
//			System.out.println(bookObjRepository.findFirstAvailableCopyByBookId(book.getISBN()));
		};
	}

}
