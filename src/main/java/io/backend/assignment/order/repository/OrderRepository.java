package io.backend.assignment.order.repository;

import io.backend.assignment.order.domain.Order;
import io.backend.assignment.util.exception.OrderNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    default Order getBy(final Long orderId) {
        return findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }
}
