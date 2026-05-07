package com.product.infrastructure.web.request;

import java.math.BigDecimal;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CreateProductRequest {
    @Schema(description = "Product name", example = "Laptop")
    @NotBlank(message = "Name is required")
    public String name;

    @Schema(description = "Product price", example = "999.99")
    @Positive(message = "Price must be greater than zero")
    public BigDecimal price;

    public String description;

}
