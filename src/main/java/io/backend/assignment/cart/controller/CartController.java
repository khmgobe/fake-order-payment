package io.backend.assignment.cart.controller;

import io.backend.assignment.cart.controller.dto.request.UpdateCartRequest;
import io.backend.assignment.cart.controller.dto.response.GetCartResponse;
import io.backend.assignment.cart.service.usecase.CartServiceUseCase;
import io.backend.assignment.cart.controller.dto.request.RegisterCartRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
class CartController {

    private final CartServiceUseCase cartServiceUseCase;

    @PostMapping("/api/v1/carts/{productId}/{customerId}")
    public ResponseEntity<Void> registerCart(
            @PathVariable final Long productId,
            @PathVariable final Long customerId,
            @RequestBody @Valid final RegisterCartRequest request) {

        cartServiceUseCase.register(productId, customerId, request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/api/v1/carts/{cartId}")
    public ResponseEntity<GetCartResponse> getCart(@PathVariable final Long cartId) {

        final GetCartResponse response = cartServiceUseCase.getCart(cartId);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/api/v1/carts/{cartId}")
    public ResponseEntity<Void> updateCart (@PathVariable final Long cartId, @RequestBody @Valid final UpdateCartRequest request) {

        cartServiceUseCase.updateCart(cartId, request);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/api/v1/carts/{cartId}")
    public ResponseEntity<Void> deleteCart (@PathVariable final Long cartId) {

        cartServiceUseCase.deleteCart(cartId);

        return ResponseEntity.ok().build();
    }
}

