package com.product.infrastructure.integration;

import java.util.Map;

import org.testcontainers.containers.RabbitMQContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class RabbitMQTestResource implements QuarkusTestResourceLifecycleManager {

    private RabbitMQContainer rabbit;

    @Override
    public Map<String, String> start() {

        rabbit = new RabbitMQContainer("rabbitmq:3-management");
        rabbit.start();

        return Map.of(
                "mp.messaging.connector.smallrye-rabbitmq.host", rabbit.getHost(),
                "mp.messaging.connector.smallrye-rabbitmq.port", rabbit.getAmqpPort().toString(),
                "mp.messaging.connector.smallrye-rabbitmq.username", "guest",
                "mp.messaging.connector.smallrye-rabbitmq.password", "guest",
                "mp.messaging.outgoing.product-events-out-test.connector", "smallrye-rabbitmq",
                "mp.messaging.outgoing.product-events-out-test.exchange.name", "product-exchange",
                "mp.messaging.outgoing.product-events-out-test.routing-key", "product.created"

        );
    }

    @Override
    public void stop() {
        rabbit.stop();
    }
}