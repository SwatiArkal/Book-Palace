package com.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.book.entity.User;
import com.book.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;
		
	// handler for home page
	@GetMapping("/")
	public String homePage() {
		return "home";
	}
	
	// handler for signup page
	@GetMapping("/signup")
	public String signupPage(User user) {
		return "signup";
	}
		
	// handler for login page
	@GetMapping("/login")
	public String loginPage(User user) {
		return "login";
	}
	
	// handler for signup process
	@PostMapping("/signupProcess")
	public String signup(@ModelAttribute("user") User user, HttpSession session, Model m) {
		User u = userService.saveUser(user);
		if(u!=null) {
			//System.out.println("success");
			session.setAttribute("msg", "Register successfully");
		}else {
			//System.out.println("error");
			session.setAttribute("msg", "Something went wrong");
		}
		return "redirect:/signup";
	}

}
