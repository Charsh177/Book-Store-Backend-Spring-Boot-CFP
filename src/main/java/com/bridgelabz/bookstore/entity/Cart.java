package com.bridgelabz.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public @Data class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cart_id")
    private Integer cartId;

    @JsonIgnoreProperties({"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserData userData;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private Integer quantity;

    public Cart(Integer quantity, Book book, UserData userData) {
        this.userData = userData;
        this.book = book;
        this.quantity = quantity;
    }
}