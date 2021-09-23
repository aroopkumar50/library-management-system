package com.psl.gems.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psl.gems.model.Book;
import com.psl.gems.model.User;
import com.psl.gems.security.CurrentUserFinder;
import com.psl.gems.service.BookService;

@Controller
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	CurrentUserFinder currentUserFinder;
	
	@Autowired
	BookService bookService;
	
	@GetMapping
	public String userHome(Model model) {
		User currentUser = currentUserFinder.getCurrentUser();
		model.addAttribute("currentUser",currentUser);
		return "user/user-home.html";
	}
	
	@GetMapping(value="/browsebooks")
	public String browseBooks(Model model)
	{
		List<Book> books;
		books = bookService.findAll();
		model.addAttribute("books", books);
		return "user/user-browse-book.html";
	}
	
// browsebooks(), yourBooks(), reserveBook()
	
}
