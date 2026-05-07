package com.product.domain.model;

public record Product(
                String id,
                String name,
                String description,
                Double price,
                Double cost) {

}