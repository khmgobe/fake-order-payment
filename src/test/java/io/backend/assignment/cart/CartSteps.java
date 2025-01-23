package io.backend.assignment.cart;

import io.backend.assignment.cart.controller.dto.request.RegisterCartRequest;
import io.backend.assignment.cart.controller.dto.request.UpdateCartRequest;
import io.backend.assignment.common.ApiTest;

import java.time.LocalDateTime;

public class CartSteps extends ApiTest {

    public static RegisterCartRequest cartRequest(final int quantity) {

        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        return new RegisterCartRequest(quantity, createdAt, updatedAt);
    }

    public static UpdateCartRequest updateCartRequest(final int quantity) {

        return new UpdateCartRequest(quantity);
    }
}
