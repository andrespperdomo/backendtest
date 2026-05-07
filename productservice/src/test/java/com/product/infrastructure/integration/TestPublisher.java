package com.product.infrastructure.integration;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class TestPublisher {

    @Channel("product-events-out-test")
    Emitter<String> emitter;

    public CompletionStage<Void> send(String message) {
        System.out.println("#######################----------- " + message);
        return emitter.send(message);
    }
}
