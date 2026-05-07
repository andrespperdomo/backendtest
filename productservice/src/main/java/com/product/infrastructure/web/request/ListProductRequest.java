package com.product.infrastructure.web.request;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import jakarta.validation.constraints.*;

public record ListProductRequest(

        @QueryParam("search") @Size(max = 100, message = "Search must be at most 100 characters") String search,

        @QueryParam("page") @DefaultValue("0") @Min(value = 0, message = "Page must be >= 0") int page,

        @QueryParam("size") @DefaultValue("10") @Min(value = 1, message = "Size must be >= 1") @Max(value = 100, message = "Size must be <= 100") int size

) {
    public ListProductRequest {

        if (search != null) {
            search = search.trim();
            if (search.isEmpty())
                search = null;
        }
    }
}