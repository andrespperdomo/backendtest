package com.product.infrastructure.web.request;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CreateProductRequest {
    @NotBlank(message = "Name is required")
    public String name;

    @Positive(message = "Price must be greater than zero")
    public BigDecimal price;

    public String description;

}
