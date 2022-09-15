package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create/{token}")
    public ResponseEntity<ResponseDTO> createOrder(@Valid @RequestBody OrderDTO orderdto, @PathVariable String token){
        Order newOrder = orderService.createOrder(orderdto, token);
        ResponseDTO dto = new ResponseDTO("Order registered successfully...",newOrder);
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }

    @GetMapping("/retrieveAllOrders")
    public ResponseEntity<ResponseDTO> getAllOrderRecords(){
        List<Order> newOrder = orderService.getAllOrderRecords();
        ResponseDTO dto = new ResponseDTO("All records retrieved successfully...",newOrder);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping("/retrieveOrder/{id}")
    public ResponseEntity<ResponseDTO> getBookRecord(@PathVariable Integer id){
        Order newOrder = orderService.getOrderRecord(id);
        ResponseDTO dto = new ResponseDTO("Record retrieved successfully...",newOrder);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<ResponseDTO> updateBookRecord(@PathVariable Integer id,@Valid @RequestBody OrderDTO orderdto){
        Order newOrder = orderService.updateOrderRecord(id,orderdto);
        ResponseDTO dto = new ResponseDTO("Record updated successfully...",newOrder);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<ResponseDTO> deleteOrderRecord(@PathVariable Integer id){
        Order newOrder = orderService.deleteOrderRecord(id);
        ResponseDTO dto = new ResponseDTO("Record deleted successfully !",newOrder);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }
}