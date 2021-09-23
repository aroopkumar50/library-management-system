package com.psl.gems.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psl.gems.model.User;
import com.psl.gems.security.CurrentUserFinder;

@Controller
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	CurrentUserFinder currentUserFinder;
	
	@GetMapping
	public String userHome(Model model) {
		User currentUser = currentUserFinder.getCurrentUser();
		model.addAttribute("currentUser",currentUser);
		return "user/user-home.html";
	}
	
// browsebooks(), yourBooks(), reserveBook()
	
}
