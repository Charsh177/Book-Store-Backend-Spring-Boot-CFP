package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.MyWishListDTO;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.MyWishList;
import com.bridgelabz.bookstore.entity.UserData;
import com.bridgelabz.bookstore.exceptions.BookException;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class MyWishlistService {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public MyWishList addToWishlist(MyWishListDTO dto) {
        Optional<UserData> user = userRepository.findById(dto.getUserId());
        Optional<Book> book = bookRepository.findById(dto.getBookId());
        if(user.isPresent() && book.isPresent()) {
            MyWishList newWishList = new MyWishList(user.get(),book.get());
            wishListRepository.save(newWishList);
            return newWishList;
        } else {
            throw new BookException("User or Book doesn't exists..!!!");
        }
    }

    public List<MyWishList> getAllWishlists() {
        List<MyWishList> list = wishListRepository.findAll();
        return list;
    }

    public List<MyWishList> getWishlistRecordById(Integer id){
        List<MyWishList> list = wishListRepository.findByWishlistId(id);
        if(list.isEmpty()) {
            throw new BookException("Wishlist doesn't exists for id " + id);
        } else {
            return list;
        }
    }

    public List<MyWishList> getWishlistRecordByBookId(Integer bookId) {
        List<MyWishList> list = wishListRepository.findByBookId(bookId);
        if(list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    public List<MyWishList> getWishlistRecordByUserId(Integer userId) {
        List<MyWishList> list = wishListRepository.findByUserId(userId);
        return list;
    }

    public MyWishList deleteWishlistRecordById(Integer Id) {
        Optional<MyWishList> list = wishListRepository.findById(Id);
        wishListRepository.deleteById(Id);
        return list.get();
    }
}