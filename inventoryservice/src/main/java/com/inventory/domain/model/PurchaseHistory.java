package com.inventory.domain.model;

import java.time.LocalDateTime;

public class PurchaseHistory {
    public Long id;
    public String productId;
    public Integer quantity;
    public String type; // PURCHASE | RESTOCK | RESERVE
    public LocalDateTime createdAt;
}
