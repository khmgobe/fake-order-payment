package io.backend.assignment.cart.service.usecase;

import io.backend.assignment.cart.controller.dto.request.RegisterCartRequest;
import io.backend.assignment.cart.controller.dto.request.UpdateCartRequest;
import io.backend.assignment.cart.controller.dto.response.GetCartResponse;

public interface CartServiceUseCase {

    void register(
            final Long productId,
            final Long customerId,
            final RegisterCartRequest request);

    GetCartResponse getCart(final Long cartId);

    void updateCart(final Long cartId, final UpdateCartRequest updateCartRequest);

    void deleteCart(final Long cartId);
}
