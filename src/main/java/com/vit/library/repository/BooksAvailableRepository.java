package com.vit.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vit.library.entity.BookAvailableEntity;

@Repository
public interface BooksAvailableRepository extends JpaRepository<BookAvailableEntity, Long> {
    
}
