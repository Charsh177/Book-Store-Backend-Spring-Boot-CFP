package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.entity.Order;
import java.util.List;

public interface IOrderService {

    public Order createOrder(OrderDTO orderdto, String token);

    public List<Order> getAllOrderRecords();

    public Order getOrderRecord(Integer id);

    public Order updateOrderRecord(Integer id, OrderDTO dto);

    public Order deleteOrderRecord(Integer id);
}