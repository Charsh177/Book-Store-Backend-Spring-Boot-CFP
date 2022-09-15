package com.bridgelabz.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "order_table")
@AllArgsConstructor
public @Data class Order {

    @Id
    @GeneratedValue
    private Integer orderID;
    private LocalDate date = LocalDate.now();
    private Integer price;
    private Integer quantity;
    private String address;
    @ManyToOne
    @JoinColumn(name="user_id")
    private UserData user;
    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;
    private boolean cancel;
    private String bookName;

    public Order(Integer price,Integer quantity, String address, Book book, UserData user, boolean cancel,String bookName) {
        this.price=price;
        this.quantity=quantity;
        this.address=address;
        this.book = book;
        this.user=user;
        this.cancel = cancel;
        this.bookName=bookName;
    }

    public Order() {
        super();
    }

    public Order( Integer quantity, String address, Book book, UserData user, boolean cancel) {
        this.price=price;
        this.quantity=quantity;
        this.address=address;
        this.book = book;
        this.user=user;
        this.cancel = cancel;
    }

    public Order(Integer price, Integer quantity, String address, Book book, UserData userData, boolean cancel, String bookName, LocalDate date) {
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.book = book;
        this.user = userData;
        this.cancel = cancel;
        this.date = date;
    }
}