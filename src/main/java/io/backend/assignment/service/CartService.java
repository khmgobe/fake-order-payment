package io.backend.assignment.service;

import io.backend.assignment.controller.dto.request.CartRequest;
import io.backend.assignment.domain.Cart;
import io.backend.assignment.domain.Customer;
import io.backend.assignment.domain.Product;
import io.backend.assignment.repository.CartRepository;
import io.backend.assignment.repository.CustomerRepository;
import io.backend.assignment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class CartService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public void register(final Long productId, final Long customerId, final CartRequest request) {

        final Product product = productRepository.findById(productId).get();
        final Customer customer = customerRepository.findById(customerId).get();

        final Cart cart = request.toDomain(product, customer);

        cartRepository.save(cart);

    }
}
