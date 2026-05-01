package com.product.domain.model;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;

@Entity
public class UserProductProjection extends PanacheEntity {

    public Long idUser;
    public String userName;
    public String userEmail;
    public String productName;
    public String productCategory;
    public boolean purchased;
    public double rating;
    public LocalDateTime lastInteraction;

    public UserProductProjection() {
    }

    public UserProductProjection(Long idUser, String userName, String userEmail,
            String productName, String productCategory,
            boolean purchased, double rating,
            LocalDateTime lastInteraction) {
        this.idUser = idUser;
        this.userName = userName;
        this.userEmail = userEmail;
        this.productName = productName;
        this.productCategory = productCategory;
        this.purchased = purchased;
        this.rating = rating;
        this.lastInteraction = lastInteraction;
    }

    @PrePersist
    void onCreate() {
        this.lastInteraction = LocalDateTime.now();
    }

}
