package com.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.book.service.BookService;
import com.book.service.UserService;
import com.book.entity.Book;
import com.book.entity.User;
import com.book.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	BookService bookService;
		
	@Autowired
	public UserService userService;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@GetMapping("/userHome")
	public String userHome() {
		return "home_page";
	}
	
	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<Book> list = bookService.getAllBook();
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("bookList");
//		mv.addObject("book", list);
//		return mv;
		return new ModelAndView("bookList", "book", list);
	}
	
	@GetMapping("/updateDetails")
    public String userUpdate(Model model) {
        // Get the currently logged-in user's email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        // Find the user by email
        User user = userRepository.findByEmail(username);
        
        if (user == null) {
            // Handle the case where the user is not found, e.g., show an error message
            return "error_page"; // Create an error page or specify a different error handling mechanism
        }
        
        model.addAttribute("user", user);
        return "user_update";
    }

    @PostMapping("/saveUpdates")
    public String saveUpdates(@ModelAttribute User user, @RequestParam("newPassword") String newPassword) {
        // Retrieve the user from the database by their ID.
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        
        if (existingUser != null) {
            // Update the user's details based on the form data.
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setAddress(user.getAddress());
            existingUser.setContact(user.getContact());

            if (!newPassword.isEmpty()) {
                // Hash the new password and update the user's password.
                String hashedPassword = passwordEncoder.encode(newPassword);
                existingUser.setPassword(hashedPassword);
            }

            // Save the updated user in the database.
            userRepository.save(existingUser);
        }

        return "home_page";
    }

    
}
