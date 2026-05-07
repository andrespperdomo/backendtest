package com.product.infrastructure.integration;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.arc.profile.IfBuildProfile;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
@IfBuildProfile("test")
public class TestConsumer {

    private final BlockingQueue<String> messages = new LinkedBlockingQueue<>();

    @PostConstruct
    void init() {
        System.out.println("🚀 TestConsumer STARTED");
    }

    @Incoming("product-events-in-test")
    public void receive(String message) {
        System.out.println("TEST RECEIVED: " + message);
        messages.add(message);
    }

    public String awaitMessage() {
        try {
            return messages.poll(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for message", e);
        }
    }

    public void clear() {
        messages.clear();
    }
}