package com.inventory.infrastructure.rabbitmq.event;

public record InventoryUpdatedEvent(
                String productId,
                Integer quantity,
                String type) {
}