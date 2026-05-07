package com.inventory.domain.model;

import com.inventory.domain.exception.InsufficientStockException;

public record Inventory(
        // Long id,
        String idProduct,
        Integer quantity) {

    public Inventory decrease(int amount) {
        if (this.quantity < amount) {
            throw new InsufficientStockException(this.quantity, amount);
        }
        return new Inventory(idProduct, quantity - amount);
    }
}