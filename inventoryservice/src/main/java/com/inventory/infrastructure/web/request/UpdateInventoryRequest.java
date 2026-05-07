package com.inventory.infrastructure.web.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class UpdateInventoryRequest {
    @Schema(description = "IdProduct", example = "1234")
    @NotBlank(message = "IdProduct is required")
    public String idProduct;

    @Schema(description = "Quantity", example = "123456")
    @Positive(message = "Quantity is required")
    public Integer quantity;

}
