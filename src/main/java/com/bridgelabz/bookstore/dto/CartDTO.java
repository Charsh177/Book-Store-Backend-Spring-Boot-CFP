package com.bridgelabz.bookstore.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

public @Data class CartDTO {
    private Integer userId;
    private Integer bookId;
    @NotNull(message="Book quantity required must")
    private Integer quantity;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}