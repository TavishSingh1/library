package com.vit.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vit.library.repository.BookRepository;
import com.vit.library.repository.BooksAvailableRepository;
import com.vit.library.service.BookService;
import com.vit.library.service.UserService;

@RestController
@RequestMapping("/rent")
public class BookIssueController {
    @Autowired
    BookService bookService;


    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserService userService;

    @Autowired
    BooksAvailableRepository booksAvailableRepository;

    @PostMapping
    public ResponseEntity<String> rentBook(@RequestParam(value = "name") String name,
            @RequestParam(value = "author") String author,
            @RequestParam(value = "userId") Long userId) {
        int response = bookService.verifyUser(author, name, userId);
        switch (response) {
            case 1:
                return ResponseEntity.ok("Book rented");
            default:
                return ResponseEntity.notFound().build();
        }
    }
}