package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface ICartService {

//    List<Cart> getCartData();
//
//    Cart getCartById(int cartID);
//
//    Cart createCart(CartDTO cartDTO);

    ResponseDTO getCartDetails();

    Optional<Cart> getCartDetailsById(Integer cartId);

    Cart updateCartById(Integer cartId, CartDTO cartDTO);

    Cart updateQuantity(Integer id, Integer quantity);

    Optional<Cart> deleteCartItemById(Integer cartId);

//    void deleteCart(int cartId);

}
