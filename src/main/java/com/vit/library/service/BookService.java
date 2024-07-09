package com.vit.library.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vit.library.dto.Book;
import com.vit.library.entity.BookAvailableEntity;
import com.vit.library.entity.BookEntity;
import com.vit.library.entity.BookIssueEntity;
import com.vit.library.entity.UserEntity;
import com.vit.library.repository.BookIssueRepository;
import com.vit.library.repository.BookRepository;
import com.vit.library.repository.BooksAvailableRepository;
import com.vit.library.repository.UserRepository;
import com.vit.library.util.Status;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BooksAvailableRepository booksAvailableRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookIssueRepository bookIssueRepository;

    public void addBook(Book book) {
        BookEntity bookEntity = new BookEntity();
        BookAvailableEntity booksAvailableEntity = new BookAvailableEntity();
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setGenre(book.getGenre());
        bookEntity.setName(book.getName());
        bookEntity.setRent(book.getRent());
        bookEntity.setQuantity(book.getQuantity());
        booksAvailableEntity.setQuantityRemaining(book.getQuantity());
        bookEntity.setBooksAvailableEntity(booksAvailableEntity);
        booksAvailableRepository.save(booksAvailableEntity);
        bookRepository.save(bookEntity);
        System.out.println(book);
    }

    public void deleteBook(Long id) {
        System.out.println("Book of " + id + " was removed");
        bookRepository.deleteById(id);
    }

    public List<Book> findAllBooks() {
        System.out.println("All books are shown");
        List<BookEntity> bookEntitys = bookRepository.findAll();
        List<Book> books = new ArrayList<>();
        for (BookEntity bookEntity : bookEntitys) {
            Book book = new Book();
            book.setAuthor(bookEntity.getAuthor());
            book.setId(bookEntity.getId());
            book.setName(bookEntity.getName());
            book.setGenre(bookEntity.getGenre());
            book.setRent(bookEntity.getRent());
            book.setQuantity(bookEntity.getQuantity());
            books.add(book);
        }
        return books;
    }

    public void updateBook(Book book) {
        System.out.println(book);
        Optional<BookEntity> optionalbookEntity = bookRepository.findById(book.getId());
        if (optionalbookEntity.isPresent()) {
            BookEntity bookEntity = optionalbookEntity.get();
            bookEntity.setAuthor(book.getAuthor());
            bookEntity.setGenre(book.getGenre());
            bookEntity.setName(book.getName());
            bookEntity.setRent(book.getRent());
            bookEntity.setQuantity(book.getQuantity());
            bookRepository.save(bookEntity);
        }
    }

    public Book findBookById(Long id) {
        Book book = new Book();
        Optional<BookEntity> optionalbookEntity = bookRepository.findById(id);
        if (optionalbookEntity.isPresent()) {
            BookEntity bookEntity = optionalbookEntity.get();
            book.setAuthor(bookEntity.getAuthor());
            book.setGenre(bookEntity.getGenre());
            book.setId(bookEntity.getId());
            book.setName(bookEntity.getName());
            book.setRent(bookEntity.getRent());
            book.setQuantity(bookEntity.getQuantity());
            System.out.println("Book with id " + id + " was found");
        }
        return book;
    }

    public List<Book> searchBook(String author, String name) {
        System.out.println("Book with author" + author + " was shown");
        List<BookEntity> bookEntitys;
        if (author != null && name != null) {
            bookEntitys = bookRepository.findByAuthorAndName(author, name);
        } else if (author != null && name == null) {
            bookEntitys = bookRepository.findByAuthor(author);
        } else {
            bookEntitys = bookRepository.findByName(name);
        }
        List<Book> books = new ArrayList<>();
        for (BookEntity bookEntity : bookEntitys) {
            Book book = new Book();
            book.setAuthor(bookEntity.getAuthor());
            book.setId(bookEntity.getId());
            book.setName(bookEntity.getName());
            book.setGenre(bookEntity.getGenre());
            book.setRent(bookEntity.getRent());
            book.setQuantity(bookEntity.getQuantity());
            books.add(book);
        }
        return books;    
    }
    private int rentBook(String author, String name, UserEntity userEntity){
        List<BookEntity> bookEntities = bookRepository.findByAuthorAndName(author, name);
        BookIssueEntity bookIssueEntity = new BookIssueEntity();
        for (BookEntity bookEntity : bookEntities) {
            BookAvailableEntity bookAvailableEntity = bookEntity.getBooksAvailableEntity();
            int quantityRemaining = bookAvailableEntity.getQuantityRemaining();

            if (quantityRemaining >=1) {
                bookAvailableEntity.setQuantityRemaining(quantityRemaining-1);
                booksAvailableRepository.save(bookAvailableEntity);
                bookIssueEntity.setBookEntity(bookEntity);
                bookIssueEntity.setIssueDate(LocalDate.now());
                bookIssueEntity.setDueDate(LocalDate.now());
                bookIssueEntity.setUserEntity(userEntity);
                bookIssueEntity.setStatus(Status.ISSUED);
                bookIssueRepository.save(bookIssueEntity);
                return 1;
            }
            else{
                return 0;
            }
        }
        return 0;     
    }

    public int verifyUser(String author,String name,Long id){
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            return rentBook(author, name, userEntity);
        }
        else{
            return 0;
        }
    }
}
