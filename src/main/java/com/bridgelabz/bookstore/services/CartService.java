package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Cart;
import com.bridgelabz.bookstore.entity.UserData;
import com.bridgelabz.bookstore.exceptions.BookException;
import com.bridgelabz.bookstore.exceptions.CartException;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartService implements ICartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    // Create a cart
    public Cart createCart(CartDTO cartDTO) {
        Optional<Book> book = bookRepository.findById(cartDTO.getBookId());
        Optional<UserData> userRegistration = userRepository.findById(cartDTO.getUserId());
        if (book.isPresent() && userRegistration.isPresent()) {
            Cart newCart = new Cart(cartDTO.getQuantity(), book.get(), userRegistration.get());
            cartRepository.save(newCart);
            return newCart;
        } else {
            throw new BookException("Book or User doesn't exists..!!!");
        }
    }

    @Override
    public ResponseDTO getCartDetails() {
        List<Cart> getCartDetails = cartRepository.findAll();
        ResponseDTO responseDTO= new ResponseDTO();
        if (getCartDetails.isEmpty()){
            String message = "the cart is empty!!!";
            responseDTO.setMessage(message);
            responseDTO.setData(0);
        } else {
            responseDTO.setMessage("the list of cart items is successfully retrieved...");
            responseDTO.setData(getCartDetails);
        }
        return responseDTO;
    }

    @Override
    public Optional<Cart> getCartDetailsById(Integer cartId) {
        Optional<Cart> getCartData = cartRepository.findById(cartId);
        if (getCartData.isPresent()){
            return getCartData;
        } else {
            throw new CartException("Didn't find any record for this cartId");
        }
    }

    @Override
    public Cart updateCartById(Integer cartId, CartDTO cartDTO) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        Optional<Book>  book = bookRepository.findById(cartDTO.getBookId());
        Optional<UserData> user = userRepository.findById(cartDTO.getUserId());
        if(cart.isPresent()) {
            if(book.isPresent() && user.isPresent()) {
                Cart newCart = new Cart(cartId, user.get(),book.get(), cartDTO.getQuantity());
                cartRepository.save(newCart);
                return newCart;
            } else {
                throw new BookException("Book or User doesn't exists..!!!");
            }
        } else {
            throw new CartException("Cart Record doesn't exists..!!!");
        }
    }

    @Override
    public Cart updateQuantity(Integer id, Integer quantity) {
        Optional<Cart> cart = cartRepository.findById(id);
        Optional<Book>  book = bookRepository.findById(cart.get().getBook().getBookId());
        if(cart.isPresent()) {
            if(quantity < book.get().getQuantity()) {
                cart.get().setQuantity(quantity);
                cartRepository.save(cart.get());
                book.get().setQuantity(book.get().getQuantity() - (quantity - cart.get().getQuantity()));
                bookRepository.save(book.get());
                return cart.get();
            } else {
                throw new BookException("Requested quantity is not available");
            }
        } else {
            throw new CartException("Cart Record doesn't exists..!!!");
        }
    }

    @Override
    public Optional<Cart> deleteCartItemById(Integer cartId) {
        Optional<Cart> deleteData = cartRepository.findById(cartId);
        if (deleteData.isPresent()){
            cartRepository.deleteById(cartId);
            return deleteData;
        } else {
            throw new CartException(" Didn't get any cart for specified cart id ");
        }
    }
}