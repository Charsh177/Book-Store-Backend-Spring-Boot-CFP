package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    BookService bookService;

    // Add New Book Into Bookstore

    @PostMapping("/create")
    public ResponseEntity<String> addBookToRepository(@Valid @RequestBody BookDTO bookDTO) {
        Book newBook = bookService.createBook(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("New Book Created in Book Store", newBook);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    // Get All

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllBookData() {
        List<Book> listOfBooks = bookService.getAllBookData();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully :", listOfBooks);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    // Get All by BookId

    @GetMapping("/getBy/{bookId}")
    public ResponseEntity<String> getBookDataById(@PathVariable int bookId) {
        Book book = bookService.getBookDataById(bookId);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully by id :", book);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    // Delete by id

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<String> deleteRecordById(@PathVariable int bookId) {
        ResponseDTO dto = new ResponseDTO("Book Record deleted successfully", bookService.deleteRecordByToken(bookId));
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    // Search by Name

    @CrossOrigin
    @GetMapping(value = "searchByBookName/{name}")
    public ResponseEntity getBookByName(@PathVariable(required = false) Optional<String> name) {
        if (name.isPresent()) {
            List<Book> updateRecord = bookService.getBookByName(name.get());
            ResponseDTO dto = new ResponseDTO(" Book is get successfully by Name", updateRecord);
            return new ResponseEntity(dto, HttpStatus.ACCEPTED);
        } else {
            ResponseDTO dto = new ResponseDTO(" Book is get successfully by Name", new Book());

            return new ResponseEntity(dto, HttpStatus.ACCEPTED);
        }
    }

    // update record by id

    @PutMapping("/updateBookById/{BookId}")
    public ResponseEntity<String> updateRecordById(@PathVariable Integer BookId, @Valid @RequestBody BookDTO bookDTO) {
        Book updateRecord = bookService.updateRecordById(BookId, bookDTO);
        ResponseDTO dto = new ResponseDTO(" Book Record updated successfully by Id", updateRecord);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }

    // Sorting Books ascending order by price
    @GetMapping("/sortingAscending")
    public ResponseEntity<ResponseDTO> getBooksInAscendingOrder() {
        List<Book> listOfBooks = bookService.sortedListOfBooksInAscendingOrder();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully :", listOfBooks);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    // Sorting Books descending order by price
    @GetMapping("/sortingDescending")
    public ResponseEntity<ResponseDTO> getBooksInDescendingOrder() {
        List<Book> listOfBooks = bookService.sortedListOfBooksInDescendingOrder();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully :", listOfBooks);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    // Update Books quantity records
    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable Integer id, @RequestParam Integer quantity) {
        Book newBook = bookService.updateQuantity(id, quantity);
        ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully...", newBook);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

}