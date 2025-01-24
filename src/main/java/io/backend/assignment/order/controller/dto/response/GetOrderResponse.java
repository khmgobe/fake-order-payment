package io.backend.assignment.order.controller.dto.response;

import io.backend.assignment.order.domain.enumeration.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record GetOrderResponse(

        @NotNull(message = "총 주문 금액은 필수입니다.")
        Long totalAmount,
        @NotNull(message = "주문상태는 필수입니다.")
        OrderStatus orderStatus,
        @NotNull(message = "주문 생성 시간은 필수입니다.")
        LocalDateTime createdAt,
        @NotNull(message = "주문 수정 시간은 필수입니다.")
        LocalDateTime updatedAt) {
}
