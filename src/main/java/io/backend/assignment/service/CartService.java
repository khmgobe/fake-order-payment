package io.backend.assignment.service;

import io.backend.assignment.controller.dto.request.CartRequest;
import io.backend.assignment.domain.Cart;
import io.backend.assignment.domain.Customer;
import io.backend.assignment.domain.Product;
import io.backend.assignment.repository.CartRepository;
import io.backend.assignment.repository.CustomerRepository;
import io.backend.assignment.repository.ProductRepository;
import io.backend.assignment.util.exception.CustomerNotFoundException;
import io.backend.assignment.util.exception.ProductNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
class CartService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;


    @Transactional
    @PostMapping("/api/v1/carts/{productId}/{customerId}")
    public ResponseEntity<Void> register(@PathVariable final Long productId, @PathVariable final Long customerId, @RequestBody @Valid final CartRequest request) {

        final Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        final Customer customer = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        final Cart cart = request.toDomain(product, customer);

        cartRepository.save(cart);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
