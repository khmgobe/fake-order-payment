package io.backend.assignment.cart.controller.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateCartRequest(

        @NotNull(message = "수량은 필수입니다.")
        Integer quantity) {
}