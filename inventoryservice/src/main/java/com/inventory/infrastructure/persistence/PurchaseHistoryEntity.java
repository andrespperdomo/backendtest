package com.inventory.infrastructure.persistence;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchase_history")
public class PurchaseHistoryEntity {

    @Id
    @GeneratedValue
    public Long id;

    public String productId;
    public Integer quantity;
    public String type;

    public LocalDateTime createdAt;
}