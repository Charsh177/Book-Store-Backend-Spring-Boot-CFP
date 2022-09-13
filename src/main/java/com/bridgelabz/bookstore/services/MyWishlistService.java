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
    private WishListRepository WishlistRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BookRepository bookRepo;

    public MyWishList addToWishlist(MyWishListDTO dto) {
        Optional<UserData> user = userRepo.findById(dto.getUserId());
        Optional<Book> book = bookRepo.findById(dto.getBookId());
        if(user.isPresent() && book.isPresent()) {
            MyWishList newWishList = new MyWishList(user.get(),book.get());
            WishlistRepo.save(newWishList);
            return newWishList;
        }
        else {
            throw new BookException("User or Book doesn't exists");
        }
    }


    public List<MyWishList> getAllWishlists() {
        List<MyWishList> list = WishlistRepo.findAll();
        return list;
    }
    public List<MyWishList> getWishlistRecordById(Integer id){
        List<MyWishList> list = WishlistRepo.findByWishlistId(id);
        if(list.isEmpty()) {
            throw new BookException("Wishlist doesn't exists for id "+id);
        }
        else {
            return list;
        }
    }

    public List<MyWishList> getWishlistRecordByBookId(Integer bookId) {
        List<MyWishList> list = WishlistRepo.findByBookId(bookId);
        if(list.isEmpty()) {
            return null;
        }
        else {
            return list;
        }
    }

    public List<MyWishList> getWishlistRecordByUserId(Integer userId) {
        List<MyWishList> list = WishlistRepo.findByUserId(userId);
        return list;
    }

    public MyWishList deleteWishlistRecordById(Integer Id) {
        Optional<MyWishList> list = WishlistRepo.findById(Id);
        WishlistRepo.deleteById(Id);
        return list.get();
    }
}