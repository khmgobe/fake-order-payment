package io.backend.assignment.cart.service;

import io.backend.assignment.cart.repository.CartRepository;
import io.backend.assignment.cart.service.usecase.CartServiceUseCase;
import io.backend.assignment.cart.controller.dto.request.CartRequest;
import io.backend.assignment.cart.domain.Cart;
import io.backend.assignment.customer.domain.Customer;
import io.backend.assignment.product.domain.Product;
import io.backend.assignment.customer.repository.CustomerRepository;
import io.backend.assignment.product.repository.ProductRepository;
import io.backend.assignment.util.exception.CustomerNotFoundException;
import io.backend.assignment.util.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService implements CartServiceUseCase {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;


    @Override
    @Transactional
    public void register(final Long productId, final Long customerId, final CartRequest request) {

        final Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        final Customer customer = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        final Cart cart = request.toDomain(product, customer);

        cartRepository.save(cart);
    }
}
