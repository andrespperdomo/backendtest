package com.product.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
public class Product extends PanacheEntity {

    @NotBlank
    @Column(nullable = false)
    public String name;

    @Column(length = 1000)
    public String description;

    @NotNull
    @Positive
    @Column(nullable = false)
    public Double price;

    @NotNull
    @Positive
    @Column(nullable = false)
    public Double cost;

    @NotNull
    @Positive
    @Column(nullable = false)
    public Integer stockQuantity;

    public Double reserveQuantity;

    public Double availableQuantity;

    public String imagePath;

    @Column(nullable = false, updatable = false)
    public LocalDateTime createdDate;

    @Column(nullable = false)
    public LocalDateTime updatedDate;

    // Reference only
    public String userId;

    public Product(String name, Double price, String description, Double cost) {
        // this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.cost = cost;

    }

    @PrePersist
    void onCreate() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }
}