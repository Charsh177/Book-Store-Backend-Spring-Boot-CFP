package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface ICartService {

    ResponseDTO getCartDetails();

    Optional<Cart> getCartDetailsById(Integer cartId);

    Cart updateCartById(Integer cartId, CartDTO cartDTO);

    Cart updateQuantity(Integer id, Integer quantity);

    Optional<Cart> deleteCartItemById(Integer cartId);
}
