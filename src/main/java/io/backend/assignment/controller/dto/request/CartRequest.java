package io.backend.assignment.controller.dto.request;

import io.backend.assignment.domain.Cart;
import io.backend.assignment.domain.Customer;
import io.backend.assignment.domain.Product;

import java.time.LocalDateTime;

public record CartRequest(

        int quantity,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public Cart toDomain(final Product product, final Customer customer) {
        return Cart
                .builder()
                .customer(customer)
                .product(product)
                .quantity(quantity)
                .createdAt(createdAt())
                .updatedAt(updatedAt())
                .build();
    }
}
