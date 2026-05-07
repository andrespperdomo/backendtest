package com.product.application.messaging;

public record EventEnvelope<T>(
        String eventType,
        T data,
        String timestamp) {
    public EventEnvelope(String eventType, T data) {
        this(eventType, data, java.time.Instant.now().toString());
    }
}
