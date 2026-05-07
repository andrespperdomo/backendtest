package com.product.application.command;

public record ListProductCommand(String search, int size, int page) {
}