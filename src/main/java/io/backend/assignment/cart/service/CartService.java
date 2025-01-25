package io.backend.assignment.cart.service;

import io.backend.assignment.cart.controller.dto.request.UpdateCartRequest;
import io.backend.assignment.cart.controller.dto.response.GetCartResponse;
import io.backend.assignment.cart.repository.CartRepository;
import io.backend.assignment.cart.service.usecase.CartServiceUseCase;
import io.backend.assignment.cart.controller.dto.request.RegisterCartRequest;
import io.backend.assignment.cart.domain.Cart;
import io.backend.assignment.customer.domain.Customer;
import io.backend.assignment.product.domain.Product;
import io.backend.assignment.customer.repository.CustomerRepository;
import io.backend.assignment.product.repository.ProductRepository;
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
    public void register(final Long productId, final Long customerId, final RegisterCartRequest request) {

        final Product product = productRepository.getBy(productId);

        final Customer customer = customerRepository.getBy(customerId);

        final Cart cart = request.toDomain(product, customer);

        cartRepository.save(cart);
    }

    @Override
    @Transactional(readOnly = true)
    public GetCartResponse getCart(final Long cartId) {

        final Cart cart = cartRepository.getBy(cartId);

        final GetCartResponse cartResponse = cart.toCartResponse(cart);

        return cartResponse;
    }

    @Transactional
    public void updateCart(final Long cartId, final UpdateCartRequest request) {

        final Cart cart = cartRepository.getBy(cartId);

        cart.changeQuantity(request.quantity());
    }

    @Transactional
    public void deleteCart(final Long cartId) {

        final Cart cart = cartRepository.getBy(cartId);

        cartRepository.deleteById(cartId);
    }
}
