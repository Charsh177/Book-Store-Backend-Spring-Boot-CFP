package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query(value = "SELECT * FROM book_store.order;", nativeQuery = true)
    List<Order> listOrder();
}