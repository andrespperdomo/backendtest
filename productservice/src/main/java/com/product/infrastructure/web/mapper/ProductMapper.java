package com.product.infrastructure.web.mapper;

import com.product.application.command.CreateProductCommand;
import com.product.domain.model.Product;
import com.product.infrastructure.web.request.CreateProductRequest;
import com.product.infrastructure.web.response.ProductResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ProductMapper {
    CreateProductCommand toCommand(CreateProductRequest request);

    // 🔹 Domain → JSON API Response
    @Mapping(target = "data.type", constant = "products")
    @Mapping(target = "data.id", source = "id")
    @Mapping(target = "data.attributes.name", source = "name")
    @Mapping(target = "data.attributes.price", source = "price")
    @Mapping(target = "data.attributes.description", source = "description")
    ProductResponse toResponse(Product product);
}
