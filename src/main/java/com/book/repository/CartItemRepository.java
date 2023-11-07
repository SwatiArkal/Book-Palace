package com.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.entity.CartItem;
import com.book.entity.User;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	public List<CartItem> findByUser(User user);

}
