package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
