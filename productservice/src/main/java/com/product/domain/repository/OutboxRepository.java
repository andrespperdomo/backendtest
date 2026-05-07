package com.product.domain.repository;

import java.util.List;

import com.product.infrastructure.persistence.OutboxEventEntity;
import com.product.infrastructure.rabbitmq.model.OutboxEvent;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface OutboxRepository extends PanacheRepository<OutboxEventEntity> {
    OutboxEvent save(OutboxEvent event);

    public List<OutboxEvent> findPending();
}
