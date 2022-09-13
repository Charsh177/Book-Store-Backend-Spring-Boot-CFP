package com.bridgelabz.bookstore.entity;

import javax.persistence.*;

@Entity
@Table(name = "my_wish_list")
public class MyWishList {

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

    public Integer getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Integer wishlistId) {
        this.wishlistId = wishlistId;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public MyWishList(UserData user, Book book) {
        super();
        this.user = user;
        this.book = book;
    }

}