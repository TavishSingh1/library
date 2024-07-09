package com.vit.library.entity;

import java.time.LocalDate;

import com.vit.library.util.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "BOOKS_ISSUED")
public class BookIssueEntity {
    @Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;

    @Column(name = "ISSUE_DATE")
    private LocalDate issueDate;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID")
    private BookEntity bookEntity;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private UserEntity userEntity;

    @Column(name = "STATUS")
    private Status status;

    public Long getId() {
        return id;
    }
    public BookEntity getBookEntity() {
        return bookEntity;
    }
    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }
    public UserEntity getUserEntity() {
        return userEntity;
    }
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
