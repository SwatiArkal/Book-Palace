package com.book.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.entity.Order;
import com.book.entity.User;
import com.book.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	// Method to create an order
	public Order createOrder(User user, double totalAmount) {
		// Create a new Order
		Order order = new Order();
		order.setUser(user);
		order.setOrderDate(new Date()); // Set the order date to the current date
		order.setTotalAmount(totalAmount);
		order.setStatus("not delivered"); // Set the initial order status

		return orderRepository.save(order);
	}

	// Method to retrieve orders by user
	public List<Order> getOrdersByUser(User user) {
		return orderRepository.findByUser(user);
	}
}
