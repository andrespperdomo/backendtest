package com.product.application.usecase;

import com.product.application.command.CreateProductCommand;
import com.product.domain.model.Product;
import com.product.domain.repository.ProductRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateProductUseCase {

    private final ProductRepository repository;

    @Inject
    public CreateProductUseCase(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    public Product execute(CreateProductCommand command) {
        Product product = new Product(
                command.name(),
                command.price(),
                command.description(),
                command.cost());
        return repository.save(product);
    }

}
