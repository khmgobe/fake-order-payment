package io.backend.assignment.cart.controller.dto.request;

import org.springframework.util.Assert;

public record UpdateCartRequest(
        Integer quantity) {

    public UpdateCartRequest {
        Assert.notNull(quantity, "수량은 필수입니다.");
    }
}