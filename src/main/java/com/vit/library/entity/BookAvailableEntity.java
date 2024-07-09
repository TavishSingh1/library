package com.vit.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BOOKS_AVAILABLE")
public class BookAvailableEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "QUANTITY_REMAINING")
    private int quantityRemaining;

    public int getQuantityRemaining() {
        return quantityRemaining;
    }

    public void setQuantityRemaining(int quantityRemaining) {
        this.quantityRemaining = quantityRemaining;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
