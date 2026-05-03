package com.product.infrastructure.rabbitmq.event;

import java.util.concurrent.CompletionStage;

import com.product.domain.model.Product;
import com.product.infrastructure.rabbitmq.model.OutboxEvent;

public interface EventPublisher {
    public CompletionStage<Void> publishProductCreated(OutboxEvent outboxEvent);
}
