package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.exceptions.BookException;
import com.bridgelabz.bookstore.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookService implements IBookService{

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book createBook(BookDTO bookDTO) {
        Book newBook = new Book(bookDTO);
        return  bookRepository.save(newBook);
    }

    @Override
    public Book getBookDataById(int bookId) {
        Optional<Book> getBook=bookRepository.findById(bookId);
        if(getBook.isPresent()){
            return getBook.get();
        }
        throw new BookException("Book Store Details for id not found");
    }

    @Override
    public List<Book> getAllBookData() {
        List<Book> getBooks = bookRepository.findAll();
        return getBooks;
    }

    @Override
    public Book updateRecordById(Integer bookId, BookDTO bookDTO) {
        Optional<Book> updateBook = bookRepository.findById(bookId);
        if (updateBook.isPresent()) {
            Book updateUser = new Book(bookId, bookDTO);
            bookRepository.save(updateUser);
            return updateUser;

        } else {
            throw new BookException("Book record doesn't found");
        }
    }

    @Override
    public Object deleteRecordByToken(int bookId) {
        Optional<Book> newBook = bookRepository.findById(bookId);
        if (newBook.isPresent()) {
            bookRepository.deleteById(bookId);
        } else {
            throw new BookException("Book record doesn't found");
        }
        return null;
    }

    @Override
    public List <Book> getBookByName(String bookName) {
        List<Book> listOfBooks = bookRepository.findByBookName(bookName);
        if(listOfBooks.isEmpty()) {
            throw new BookException("No Book found with this name.. sorry !!!");
        }
        return listOfBooks;
    }

    @Override
    public List<Book> sortedListOfBooksInAscendingOrder() {
        List<Book> getSortedList=  bookRepository.getSortedListOfBooksInAsc();
        return getSortedList;
    }

    @Override
    public List<Book> sortedListOfBooksInDescendingOrder() {
        List<Book> getSortedListInDesc =  bookRepository.getSortedListOfBooksInDesc();
        return getSortedListInDesc;
    }

    @Override
    public Book updateQuantity(Integer id, Integer quantity) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()) {
            book.get().setQuantity(quantity);
            bookRepository.save(book.get());
            return book.get();
        }
        else {
            throw new BookException("Book Record doesn't exists");
        }
    }
}