package io.backend.assignment.order.controller.dto.request;

import io.backend.assignment.cart.domain.Cart;
import io.backend.assignment.customer.domain.Customer;
import io.backend.assignment.order.domain.Order;
import io.backend.assignment.order.domain.enumeration.OrderStatus;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public record RegisterOrderRequest(
        @NotNull(message = "주문상태는 필수입니다.") OrderStatus orderStatus,
        @NotNull(message = "주문 생성 시간은 필수입니다.") LocalDateTime createdAt,
        @NotNull(message = "주문 수정 시간은 필수입니다.") LocalDateTime updatedAt) {

    public Order toDomain(final Customer customer, final List<Cart> carts, final Long totalAmount) {

        return Order.builder()
                .customer(customer)
                .carts(carts)
                .totalAmount(totalAmount)
                .orderStatus(OrderStatus.PENDING)
                .createdAt(createdAt())
                .updatedAt(updatedAt())
                .build();
    }
}
