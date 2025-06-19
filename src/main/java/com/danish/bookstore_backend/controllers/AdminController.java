package com.danish.bookstore_backend.controllers;

import com.danish.bookstore_backend.dto.BookDTO;
import com.danish.bookstore_backend.security.jwt.JwtUtils;
import com.danish.bookstore_backend.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/api/admin/secure")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Admin Controller")
public class AdminController {

    private final BookService bookService;
    private final JwtUtils jwtUtils;

    @Autowired
    public AdminController(BookService bookService, JwtUtils jwtUtils) {
        this.bookService = bookService;
        this.jwtUtils = jwtUtils;
    }

    private String extractEmail(String token) {
        String jwt = token.substring(7);
        return jwtUtils.extractPersonEmail(jwt);
    }

    @Operation(summary = "Add new book to DataBase.",
            description = "Requires a BookDTO object as a request body.")
    @PostMapping("/add-book")
    public ResponseEntity<BookDTO> postBook(@RequestBody @Valid BookDTO bookDTO,
                                            BindingResult bindingResult) {

        BookDTO responseBody = bookService.addBook(bookDTO, bindingResult);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @Operation(summary = "Increase quantity of a specific book by 1.",
            description = "Changes copies and copies available fields of a selected book.")
    @PatchMapping("/increase-quantity/{bookId}")
    public ResponseEntity<HttpStatus> increaseBookQuantity(@PathVariable("bookId") Long bookId) {

        bookService.changeQuantity(bookId, "increase");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Decrease quantity of a specific book by 1.",
            description = "Changes copies and copies available fields of a selected book.")
    @PatchMapping("/decrease-quantity/{bookId}")
    public ResponseEntity<HttpStatus> decreaseBookQuantity(@PathVariable("bookId") Long bookId) {

        bookService.changeQuantity(bookId, "decrease");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete a book from a DataBase.",
            description = "Permanently deletes a book entity from a DataBase.")
    @DeleteMapping("/delete-book/{bookId}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("bookId") Long bookId) {

        bookService.deleteById(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
