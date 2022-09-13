package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.entity.Book;

import java.util.List;


public interface IBookService {
    Book createBook(BookDTO bookDTO);

    Book getBookDataById(int BookId);

    List<Book> getAllBookData();

    Book updateRecordById(Integer BookId, BookDTO bookDTO);

    Object deleteRecordByToken(int BookId);

    List<Book> getBookByName(String bookName);

    List<Book> sortedListOfBooksInAscendingOrder();

    List<Book> sortedListOfBooksInDescendingOrder();

    Book updateQuantity(Integer id, Integer quantity);

}
