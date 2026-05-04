package com.product.domain.repository;

import java.util.Optional;
import com.inventory.domain.model.inventory;

import com.product.shared.utils.PageResult;

public interface InventoryRepository {

    Inventory save(Product product);

    Optional<Product> findById(Long id);

    public PageResult<Product> findAllPage(String search, int page, int size);

}
