package com.product.application.command;

public record CreateProductCommand(
        String name,
        Double price,
        String description,
        Double cost) {
}
