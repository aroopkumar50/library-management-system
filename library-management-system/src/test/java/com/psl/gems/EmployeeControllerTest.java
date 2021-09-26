package com.psl.gems;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.psl.gems.controller.EmployeeController;
import com.psl.gems.dao.BookObjRepository;
import com.psl.gems.dao.BookRepository;
import com.psl.gems.dao.IssueRepository;
import com.psl.gems.dao.UserRepository;
import com.psl.gems.service.BookObjService;
import com.psl.gems.service.BookService;

@WebMvcTest(EmployeeController.class)
@ComponentScan(basePackages="com.psl.gems")
class EmployeeControllerTest {
	
		@Autowired
		BCryptPasswordEncoder pwEncoder;
		
		@Autowired
		private MockMvc mockMVC;
		
		@MockBean
		private BookService bookService;
		
		@MockBean 
		private BookObjService bookObjService;
		
		@MockBean
		private UserRepository userRepo;
		
		@MockBean
		private BookRepository bookRepo;
		
		@MockBean
		private BookObjRepository bookObjRepo;
		
		@MockBean
		private IssueRepository issueRepo;
		
		@MockBean
		private DataSource ds;
		
		@Test
		public void testBook() throws Exception
		{
			String url = "/books";
			mockMVC.perform(get(url)).andExpect(status().isOk());
		}
		
}

