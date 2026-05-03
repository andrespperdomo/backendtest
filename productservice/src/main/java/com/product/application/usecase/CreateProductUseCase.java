package com.product.application.usecase;

import com.product.application.command.CreateProductCommand;
import com.product.domain.model.Product;
import com.product.domain.repository.OutboxRepository;
import com.product.domain.repository.ProductRepository;
import com.product.infrastructure.rabbitmq.event.ProductCreatedEvent;
import com.product.infrastructure.rabbitmq.model.OutboxEvent;
import com.product.infrastructure.rabbitmq.enums.Status;
import com.product.shared.utils.JsonUtil;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateProductUseCase {

    private final ProductRepository repository;

    private final OutboxRepository outboxRepository;

    @Inject
    public CreateProductUseCase(ProductRepository productRepository, OutboxRepository outboxRepository) {
        this.repository = productRepository;
        this.outboxRepository = outboxRepository;
    }

    public Product execute(CreateProductCommand command) {
        // 1. Build domain object (NO ID from command)
        Product product = new Product(
                "",
                command.name(),
                command.description(),
                command.price(),
                command.cost());

        // 3. Save product
        Product saved = repository.save(product);

        // 4. Create OUTBOX event
        ProductCreatedEvent event = new ProductCreatedEvent();
        event.id = saved.id();
        event.name = saved.name();
        event.price = saved.price();

        outboxRepository.save(new OutboxEvent(
                Long.parseLong(saved.id()),
                saved.id().toString(),
                "PRODUCT_CREATED",
                JsonUtil.toJson(event),
                Status.PENDING.name(), 0));
        return saved;
    }

}
