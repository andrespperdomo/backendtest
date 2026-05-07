package com.inventory.infrastructure.rabbitmq.event;

import java.util.concurrent.CompletionStage;
import com.inventory.infrastructure.rabbitmq.model.OutboxEvent;

public interface EventPublisher {
    public CompletionStage<Void> publishProductCreated(OutboxEvent outboxEvent);
}
