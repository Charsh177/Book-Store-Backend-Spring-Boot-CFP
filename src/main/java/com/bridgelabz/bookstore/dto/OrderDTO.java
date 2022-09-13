package com.bridgelabz.bookstore.dto;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;

public @Data class OrderDTO {
    private Integer quantity;
    @NotEmpty(message="Please provide address")
    private String address;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date(System.currentTimeMillis());
    private Integer price;
    private Integer userId;
    private Integer bookId;
    private boolean cancel;

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public boolean isCancel() {
        return cancel;
    }
    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderDTO(Integer quantity, @NotEmpty(message = "Please provide address") String address, Integer userId,
                    Integer bookId, boolean cancel, Integer price) {
        super();
        this.quantity = quantity;
        this.address = address;
        this.userId = userId;
        this.bookId = bookId;
        this.cancel = cancel;
    }
    public Integer getBookId() {
        return bookId;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public OrderDTO() {
        super();
    }

}