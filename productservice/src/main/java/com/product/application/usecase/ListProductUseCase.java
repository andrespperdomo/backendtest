package com.product.application.usecase;

import com.product.application.command.ListProductCommand;
import com.product.domain.model.Product;
import com.product.domain.repository.ProductRepository;
import com.product.shared.utils.PageResult;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ListProductUseCase {

    private final ProductRepository productRepository;

    @Inject
    public ListProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public PageResult<Product> execute(ListProductCommand command) {
        return productRepository.findAllPage(command.search(), command.page(), command.size());
    }

}
