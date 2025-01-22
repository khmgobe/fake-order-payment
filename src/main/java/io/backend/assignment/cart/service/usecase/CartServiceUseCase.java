package io.backend.assignment.cart.service.usecase;

import io.backend.assignment.cart.controller.dto.request.CartRequest;

public interface CartServiceUseCase {

    void register(
            final Long productId,
            final Long customerId,
            final CartRequest request);
}
