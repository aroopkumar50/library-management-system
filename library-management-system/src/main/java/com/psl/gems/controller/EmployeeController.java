package com.psl.gems.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.psl.gems.model.Book;
import com.psl.gems.model.BookObj;
import com.psl.gems.model.Issue;
import com.psl.gems.model.User;
import com.psl.gems.security.CurrentUserFinder;
import com.psl.gems.service.BookObjService;
import com.psl.gems.service.BookService;
import com.psl.gems.service.IssueService;
import com.psl.gems.service.UserService;

@Controller
@RequestMapping(value="/employee")
public class EmployeeController 
{
	@Autowired
	UserService usService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	BookObjService bookObjService;
	
	@Autowired
	IssueService issueService;
	
	@Autowired
	CurrentUserFinder currentUserFinder;
	
	@GetMapping
	public String employeeHomePage(Model model) {
		int currentUserId = currentUserFinder.getCurrentUserId();
		User currentUser = usService.findById(currentUserId);
		model.addAttribute("currentUser", currentUser);
		return"employee/employee-home.html";
	}
	
	@GetMapping(value="/users/showusers")
	public String showUsers(Model model,
			@RequestParam (required=false)String name, 
			@RequestParam (required=false)String showAllUsers) {
		
		List<User> users = new ArrayList<User>();
		
		if (showAllUsers != null) users = usService.findAll();
		else if (name != null) users = usService.getByName(name);
						
		return "employee/employee-show-users.html";	
	}
	
	@GetMapping(value="/users/showuserinfo")
	public String showUserInfo(@RequestParam int userId, 
								Model model) {
		User user = usService.findById(userId);
		model.addAttribute("user", user);
		return "employee/employee-show-user-info.html";
	}
	
	@GetMapping(value="/books")
	public String books() {
		return "employee/employee-books.html";
	}
	
	@GetMapping(value="/books/showbooks")
	public String showBooks(Model model,
			@RequestParam (required=false) String title,
			@RequestParam (required=false) String author,
			@RequestParam (required=false) String showAllBooks) {
		
		List<Book> books;
		if (showAllBooks == null) books = bookService.searchBooks(title, author);	
		else books = bookService.findAll();
		
		model.addAttribute("books", books);
		return "employee/employee-show-books.html";
	}
	
	@GetMapping(value="/books/newbook")
	public String newBook(Model model) {
		model.addAttribute("book", new Book());
		return "employee/employee-new-book.html";
	}
	
	@PostMapping(value="/books/save")
	public String saveBook(Book book) {
		bookService.save(book);
		BookObj bookObj = new BookObj();
		bookObj.setBook(book);
		bookObjService.save(bookObj);
		return "redirect:/employee/books/booksaved";
	}
	
	@GetMapping(value="/books/booksaved")
	public String bookSaved() {
		return "employee/employee-book-saved.html";
	}
	
	@GetMapping(value="/books/areyousuretodeletebook")
	public String areYouSureToDeleteBook(@RequestParam Long deleteBookISBN, Model model) {
		Book book = bookService.findById(deleteBookISBN);
		model.addAttribute("deleteBook", book);
		return "employee/employee-delete-book.html";
	}
	
	@DeleteMapping(value="/books/deletebook")
	public String deleteBook(@RequestParam Long deleteBookISBN) {
		bookService.deleteById(deleteBookISBN);
		return "redirect:/employee/books/bookdeleted";
	}
	
	@GetMapping(value="/books/bookdeleted")
	public String bookDeleted() {
		return "employee/employee-book-deleted.html";
	}
	
	@GetMapping(value="/books/changebookinfo")
	public String changeBookInfo(@RequestParam Long changeBookId, Model model) {
		Book book = bookService.findById(changeBookId);
		model.addAttribute("book", book);
		return "employee/employee-change-book-info.html";
	}
	
	@PutMapping(value="/books/savebookchange")
	public String updatebookinfo(Book book) {
		bookService.save(book);
		return "redirect:/employee/books/bookinfochanged";
	}
	
	@GetMapping(value="/issues")
	public String checkIssues(Model model) {
		// TODO: ENABLE filtering for issues by status
		List<Issue> issues = issueService.findAll();
		model.addAttribute("issues", issues);
		// TODO
		return "employee/view-issues.html";
	}
	
	@PostMapping(value="/issues/update")
	public String updateIssue(Issue issue) {
		issueService.save(issue);
		return "redirect:/employee/issues";
	}
	// changeBookInfo(), updateBookInfo(), returnBooks(), reservations()
}
