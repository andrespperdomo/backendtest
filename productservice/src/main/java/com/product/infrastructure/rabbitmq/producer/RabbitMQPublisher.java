package com.product.infrastructure.rabbitmq.producer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import com.product.domain.model.Product;
import com.product.infrastructure.rabbitmq.event.EventPublisher;
import com.product.infrastructure.rabbitmq.event.ProductCreatedEvent;
import com.product.infrastructure.rabbitmq.model.OutboxEvent;
import org.eclipse.microprofile.reactive.messaging.Message;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RabbitMQPublisher implements EventPublisher {

    @Channel("product-events-out")
    @Inject
    Emitter<String> emitter;

    public CompletionStage<Void> publishProductCreated(OutboxEvent event) {

        Message<String> message = Message.of(event.payload())
                .withAck(() -> {
                    // ✅ Broker confirmed delivery
                    return CompletableFuture.completedFuture(null);
                })
                .withNack(throwable -> {
                    System.err.println("Failed to send event: " + event.id());
                    throwable.printStackTrace();
                    return CompletableFuture.completedFuture(null);
                });

        return emitter.send(message.getPayload());
    }

}
