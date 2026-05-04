package com.inventory.infrastructure.web.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import com.inventory.domain.exception.InventoryNotFoundException;
import com.inventory.domain.exception.InsufficientStockException;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException ex) {

        // 404 - Not Found
        if (ex instanceof InventoryNotFoundException e) {
            return build(
                    Response.Status.NOT_FOUND,
                    "INVENTORY_NOT_FOUND",
                    e.getMessage());
        }

        // 406 - Business rule violation
        if (ex instanceof InsufficientStockException e) {
            return build(
                    Response.Status.CONFLICT,
                    "INSUFFICIENT_RESOURCES",
                    e.getMessage());
        }

        // 500 - unexpected errors
        return build(
                Response.Status.INTERNAL_SERVER_ERROR,
                "INTERNAL_ERROR",
                "Unexpected error occurred");
    }

    private Response build(Response.Status status, String code, String message) {
        return Response.status(status)
                .entity(new ApiError(code, message))
                .build();
    }
}