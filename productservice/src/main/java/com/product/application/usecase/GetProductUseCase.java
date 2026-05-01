package com.product.application.usecase;

import java.util.Optional;

import com.product.domain.model.Product;
import com.product.domain.repository.ProductRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetProductUseCase {

    private final ProductRepository repository;

    @Inject
    public GetProductUseCase(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    public Optional<Product> execute(String id) {
        return repository.findById(Long.parseLong(id));
    }

}
