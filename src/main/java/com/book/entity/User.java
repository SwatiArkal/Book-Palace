package com.book.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotBlank(message = "Name is required")
	private String name;

	@Column(unique = true)
	@Email(message = "Please enter a valid email address")
	private String email;

	// @Size(min = 8, message = "Password must be at least 8 characters long")
	// @Pattern(regexp =
	// "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&!])[A-Za-z\\d@#$%^&!]{8,}$",
	// message = "Password must contain at least one uppercase letter, one lowercase
	// letter, one digit, and one special character (@#$%^&!)")
	private String password;

	@NotBlank(message = "Address is required")
	private String address;

	@Pattern(regexp = "\\d{10}", message = "Please enter a 10-digit contact number")
	private String contact;

	private String role;

	@OneToMany(mappedBy = "user")
	private List<Order> orders = new ArrayList<>();

	public User(int id, @NotBlank(message = "Name is required") String name,
			@Email(message = "Please enter a valid email address") String email,
			@Size(min = 8, message = "Password must be at least 8 characters long") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&!])[A-Za-z\\d@#$%^&!]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character (@#$%^&!)") String password,
			@NotBlank(message = "Address is required") String address,
			@Pattern(regexp = "\\d{10}", message = "Please enter a 10-digit contact number") String contact,
			String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.contact = contact;
		this.role = role;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", address="
				+ address + ", contact=" + contact + ", role=" + role + "]";
	}

}
