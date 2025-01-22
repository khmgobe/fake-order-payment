package io.backend.assignment.cart.controller.dto.request;

import io.backend.assignment.cart.domain.Cart;
import io.backend.assignment.customer.domain.Customer;
import io.backend.assignment.product.domain.Product;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CartRequest(

        @NotNull(message = "수량은 필수입니다.")
        int quantity,
        @NotNull(message = "생성 시간은 필수입니다.")
        LocalDateTime createdAt,
        @NotNull(message = "수정 시간은 필수입니다.")
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
