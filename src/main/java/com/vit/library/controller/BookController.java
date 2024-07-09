package com.vit.library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.vit.library.dto.Book;
import com.vit.library.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<List<Book>> findAllBooks() {
        System.out.println("Books were shown");
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBook(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "author", required = false) String author) {
        System.out.println("Name= " + name + " Author= " + author);
        return ResponseEntity.ok(bookService.searchBook(author, name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        System.out.println("Book of id:" + id);
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    // @GetMapping(value="/genre")
    // public void showBookByGenre(){
    // System.out.println("Books of specific genre were shown");
    // }

    // @GetMapping(value="/rent")
    // public void showBookByRent(){
    // System.out.println("Books under a specific rent were shown");
    // }

    // @GetMapping(value = "/rating")
    // public void showBookByRating(){
    // System.out.println("Books of specific rating were shown");
    // }

    @PutMapping
    public void updateBook(@RequestBody Book book) {
        System.out.println(book + "was updated");
        bookService.updateBook(book);
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        System.out.println("in add book method");
        System.out.println(book);
        bookService.addBook(book);
        return ResponseEntity.ok("Book was added");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id) {
        System.out.println("Deleting book with id: " + id);
        bookService.deleteBook(id);
        return ResponseEntity.ok("deleted");
    }
}