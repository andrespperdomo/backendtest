package com.product.shared.utils;

import java.util.List;

public record PageResult<T>(
        List<T> data,
        long total,
        int page,
        int size) {
}