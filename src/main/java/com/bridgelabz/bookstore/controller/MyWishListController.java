package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.MyWishListDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.entity.MyWishList;
import com.bridgelabz.bookstore.services.MyWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/wishlist")
public class MyWishListController {
    @Autowired
    private MyWishlistService wishlistService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> addToWishlist(@RequestBody MyWishListDTO myWishListDTO) {
        MyWishList myWishList = wishlistService.addToWishlist(myWishListDTO);
        ResponseDTO dto = new ResponseDTO("Wishlist added successfully...",myWishList);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping("/retrieveAll")
    public ResponseEntity<ResponseDTO> getAllWishlists() {
        List<MyWishList> wishlist = wishlistService.getAllWishlists();
        ResponseDTO dto = new ResponseDTO("All Wishlist records retrieved successfully...",wishlist);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<ResponseDTO> getWishlistRecordById(@PathVariable Integer id) {
        List<MyWishList> wishlist = wishlistService.getWishlistRecordById(id);
        ResponseDTO dto = new ResponseDTO("Wishlist record retrieved successfully for given id",wishlist);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping("/retrieveByBookId/{bookId}")
    public ResponseEntity<ResponseDTO> getWishlistRecordByBookId(@PathVariable Integer bookId) {
        List<MyWishList> wishlist = wishlistService.getWishlistRecordByBookId(bookId);
        ResponseDTO dto = new ResponseDTO("Wishlist record retrieved successfully for given Book Id",wishlist);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping("/retrieveByUserId/{userId}")
    public ResponseEntity<ResponseDTO> getWishlistRecordByUserId(@PathVariable Integer userId) {
        List<MyWishList> wishlist = wishlistService.getWishlistRecordByUserId(userId);
        ResponseDTO dto = new ResponseDTO("Wishlist record retrieved successfully for given User Id",wishlist);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteWishlistRecordById(@PathVariable Integer id) {
        MyWishList wishlist = wishlistService.deleteWishlistRecordById(id);
        ResponseDTO dto = new ResponseDTO("Wishlist record deleted successfully for given Id",wishlist);
        return new ResponseEntity(dto,HttpStatus.OK);
    }
}