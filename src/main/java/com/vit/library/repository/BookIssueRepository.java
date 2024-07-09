package com.vit.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vit.library.entity.BookIssueEntity;

@Repository
public interface BookIssueRepository extends JpaRepository<BookIssueEntity,Long> {
    
}
