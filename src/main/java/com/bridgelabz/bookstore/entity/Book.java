package com.bridgelabz.bookstore.entity;

import com.bridgelabz.bookstore.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data

public class Book {
    @Id
    @GeneratedValue
    private Integer bookId;
    private String bookName;
    private String author;
    private String coverImage;
    private Integer price;
    private Integer quantity;


    public Book() {
    }
    public Integer getBookId() {
        return bookId;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverImage() {
        return coverImage;
    }
    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
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
    public Book(BookDTO bookDTO){
        this.author = bookDTO.getAuthorName();
        this.coverImage = bookDTO.getCoverImage();
        this.price=bookDTO.getPrice();
        this.quantity=bookDTO.getQuantity();
        this.bookName=bookDTO.getBookName();
    }
    public Book(Integer bookId, BookDTO bookDTO){
        this.bookId = bookId;
        this.bookName = bookDTO.getBookName();
        this.author = bookDTO.getAuthorName();
        this.coverImage = bookDTO.getCoverImage();
        this.quantity = bookDTO.getQuantity();
        this.price = bookDTO.getPrice();
    }
}
