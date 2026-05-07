package com.product.infrastructure.web.response;

import java.util.List;

public record ProductListResponse(
        List<ProductResponse> data,
        Meta meta) {

}
