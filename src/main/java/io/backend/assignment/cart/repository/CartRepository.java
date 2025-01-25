package io.backend.assignment.cart.repository;

import io.backend.assignment.cart.domain.Cart;
import io.backend.assignment.util.exception.CartNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByCustomerId(Long customerId);

    default Cart getBy(final Long cartId) {
        return findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId));
    }
}
