package com.bridgelabz.bookstore.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "wish_list")
public @Data class MyWishList {

    @Id
    @GeneratedValue
    private Integer wishlistId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserData user;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

    public MyWishList() {

    }

    public MyWishList(UserData user, Book book) {
        super();
        this.user = user;
        this.book = book;
    }
}