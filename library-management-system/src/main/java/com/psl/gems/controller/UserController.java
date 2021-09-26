package com.psl.gems.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.psl.gems.model.Book;
import com.psl.gems.model.BookObj;
import com.psl.gems.model.Issue;
import com.psl.gems.model.IssueStatus;
import com.psl.gems.model.User;
import com.psl.gems.security.CurrentUserFinder;
import com.psl.gems.service.BookObjService;
import com.psl.gems.service.BookService;
import com.psl.gems.service.IssueService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	CurrentUserFinder currentUserFinder;

	@Autowired
	BookService bookService;
	
	@Autowired
	BookObjService bookObjService;
	
	@Autowired
	IssueService issueService;

	@GetMapping
	public String userHome(Model model) {
		User currentUser = currentUserFinder.getCurrentUser();
		model.addAttribute("currentUser", currentUser);
		return "user/user-home.html";
	}

	@GetMapping(value = "/browsebooks")
	public String browseBooks(Model model, @RequestParam(required = false) String title,
			@RequestParam(required = false) String author) {
		List<Book> books;
		if ((title == null || title.isEmpty()) && (author == null || author.isEmpty())) {
			books = bookService.findAll();
		} else {
			books = bookService.searchBooks(title, author);
		}
		model.addAttribute("books", books);
		return "user/user-browse-book.html";
	}
	
	@GetMapping(value="/books/{bookId}")
	public String viewBook(@PathVariable Long bookId, Model model) {
		Book book = bookService.findById(bookId);
		boolean available = bookService.checkAvailabilityById(bookId);
		model.addAttribute("book", book);
		model.addAttribute("isAvailable", available);
		return "user/user-view-book.html";
	}
	
	@PostMapping(value="books/reserve/{bookId}")
	public String reserveBook(@PathVariable Long bookId, @RequestParam Map<String, String> requestBody) {
		Book book = bookService.findById(bookId);
		BookObj bookObj = bookObjService.getAvailableCopyByBookId(book.getISBN());
		if (bookObj == null) {
			// TODO: Redirect and say that book is unavailable.
			return "redirect:/user/browsebooks";
		}
		User currentUser = currentUserFinder.getCurrentUser();
		LocalDate issueDate = LocalDate.parse(requestBody.get("issueDate"));
		LocalDate returnDate = LocalDate.parse(requestBody.get("returnDate"));
		requestBody.get("returnDate");
		Issue issue = new Issue();
		issue.setBookObj(bookObj);
		issue.setUser(currentUser);
		issue.setIssueDate(issueDate);
		issue.setReturnDate(returnDate);
		issue.setStatus(IssueStatus.RESERVATION);
		issueService.save(issue);
		// TODO: Redirect and say reservation made successfully.
		return "redirect:/user/browsebooks";
	}
	
	@GetMapping(value="/yourreservations")
	public String viewReservations(Model model) {
		User currentUser = currentUserFinder.getCurrentUser();
		List<Issue> issues = issueService.findByUser(currentUser);
		model.addAttribute("issues", issues);
		return "user/user-book-reservation.html";
	}

// browsebooks(), yourBooks(), reserveBook()

}
