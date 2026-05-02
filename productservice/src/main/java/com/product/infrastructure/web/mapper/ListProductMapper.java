package com.product.infrastructure.web.mapper;

import com.product.application.command.ListProductCommand;
import com.product.domain.model.Product;
import com.product.infrastructure.web.request.ListProductRequest;
import com.product.infrastructure.web.response.Meta;
import com.product.infrastructure.web.response.ProductListResponse;
import com.product.infrastructure.web.response.ProductResponse;
import com.product.shared.utils.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ListProductMapper {
        ListProductCommand toCommand(ListProductRequest request);

        @Mapping(target = "data.type", constant = "products")
        @Mapping(target = "data.id", source = "id")
        @Mapping(target = "data.attributes.name", source = "name")
        @Mapping(target = "data.attributes.price", source = "price")
        @Mapping(target = "data.attributes.description", source = "description")
        ProductResponse toResponse(Product product);

        // 🔹 Domain → JSON API Response
        // @Mapping(target = "data.type", constant = "products")
        // @Mapping(target = "data.id", expression =
        // "java(String.valueOf(product.getId()))")
        default ProductListResponse toResponse(PageResult<Product> page) {
                return new ProductListResponse(
                                page.data().stream()
                                                .map(this::toResponse)
                                                .toList(),
                                new Meta(
                                                page.total(),
                                                page.page(),
                                                page.size()));
        }
}