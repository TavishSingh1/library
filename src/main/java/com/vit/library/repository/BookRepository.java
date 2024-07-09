package com.vit.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vit.library.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByAuthor(String author);
    List<BookEntity> findByName(String name);
    List<BookEntity> findByAuthorAndName(String author, String name);
}
