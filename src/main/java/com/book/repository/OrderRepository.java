package com.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.book.entity.Order;
import com.book.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByUser(User user);

}
