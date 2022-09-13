package com.bridgelabz.bookstore.dto;

import lombok.Data;

//
//@Data
//public class BookDTO {
//    public String bookName;
//    public String author;
//    public float price;
//    public String coverImage;
//    public int quantity;
//
//    public BookDTO() {
//
//    }
//
//}

import javax.validation.constraints.NotNull;

@Data
public class BookDTO {

    @NotNull(message = "book name can't be null")
    private String bookName;
    @NotNull(message = "author name can't be null")
    private String author;
    @NotNull(message = "book image cant be null")
    private String coverImage;
    @NotNull (message = "price cant be empty")
    private Integer price;
    @NotNull(message = "Quantity cant be empty")
    private Integer quantity;
    public String getBookName() {
        return bookName;
    }
    public BookDTO() {
        super();
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getAuthorName() {
        return author;
    }
    public void setAuthorName(String authorName) {
        this.author = author;
    }
    public String getCoverImage() {
        return coverImage;
    }
    public void setCoverImage(String bookImage) {
        this.coverImage = bookImage;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
