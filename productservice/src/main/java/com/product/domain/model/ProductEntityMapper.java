package com.product.domain.model;

import com.product.infrastructure.persistence.ProductEntity;

public class ProductEntityMapper {

    public static ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        // entity.setId(product.id);
        entity.setName(product.name());
        entity.setDescription(product.description());
        entity.setCost(product.cost());
        entity.setPrice(product.price());

        return entity;
    }

    public static Product toDomain(ProductEntity entity) {
        return new Product(
                String.valueOf(entity.getId()),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getCost());
    }
}