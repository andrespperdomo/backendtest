package com.inventory.domain.model;

import com.inventory.infrastructure.persistence.OutboxEventEntity;
import com.inventory.infrastructure.rabbitmq.model.OutboxEvent;

public class OutboxEntityMapper {

    public static OutboxEventEntity toEntity(OutboxEvent outbox) {
        OutboxEventEntity entity = new OutboxEventEntity();
        // entity.setId(product.id);
        entity.aggregateId = outbox.aggregateId();
        entity.payload = outbox.payload();
        entity.status = outbox.status();
        entity.type = outbox.type();
        return entity;
    }

    public static OutboxEvent toDomain(OutboxEventEntity entity) {
        return new OutboxEvent(
                entity.aggregateId,
                entity.type,
                entity.payload,
                entity.status, entity.retries);
    }
}