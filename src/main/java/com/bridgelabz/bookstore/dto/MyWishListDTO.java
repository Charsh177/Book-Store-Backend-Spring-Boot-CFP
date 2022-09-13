package com.bridgelabz.bookstore.dto;

public class MyWishListDTO {

    private Integer userId;
    private Integer bookId;

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getBookId() {
        return bookId;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

}
