package io.backend.assignment.cart.repository;

import io.backend.assignment.cart.domain.Cart;
import io.backend.assignment.util.exception.CartNotFoundException;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByCustomerId(Long customerId);

    default Cart getBy(final Long cartId) {
        return findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
    }
}
