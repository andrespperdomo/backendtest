package com.product.domain.model;

import com.product.infrastructure.persistence.ProductEntity;

public class ProductEntityMapper {

    public static ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.id);
        entity.setName(product.name);
        entity.setPrice(product.price);
        entity.setDescription(product.description);
        return entity;
    }

    public static Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getName(),
                entity.getPrice(),
                entity.getDescription(),
                entity.getCost());
    }
}