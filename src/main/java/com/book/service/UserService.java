package com.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.book.entity.Book;
import com.book.entity.User;
import com.book.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

//	public void save(User user) {
//		userRepository.save(user);
//	}

	public User saveUser(User user) {
		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		user.setRole("ROLE_USER");
		User newuser = userRepository.save(user);
		return newuser;
	}

	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();
		session.removeAttribute("msg");
	}

	// Retrieve user details by ID
	public User getUserById(int userId) {
		return userRepository.findById(userId).orElse(null);
	}

	// Update user details
	public void updateUserDetails(User updatedUser) {
		// Retrieve the existing user details
		User existingUser = getUserById(updatedUser.getId());

		if (existingUser != null) {
			// Update the existing user's details with the new values
			existingUser.setName(updatedUser.getName());
			existingUser.setEmail(updatedUser.getEmail());
			existingUser.setPassword(updatedUser.getPassword());
			existingUser.setAddress(updatedUser.getAddress());
			existingUser.setContact(updatedUser.getContact());
			// You can update other fields as needed

			// Save the updated user back to the database
			userRepository.save(existingUser);
		}
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
