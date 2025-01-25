package io.backend.assignment.order.service.usecase;

import io.backend.assignment.cart.domain.Cart;
import io.backend.assignment.cart.repository.CartRepository;
import io.backend.assignment.customer.domain.Customer;
import io.backend.assignment.customer.repository.CustomerRepository;
import io.backend.assignment.order.controller.dto.request.RegisterOrderRequest;
import io.backend.assignment.order.controller.dto.response.GetOrderResponse;
import io.backend.assignment.order.domain.Order;
import io.backend.assignment.order.repository.OrderRepository;
import io.backend.assignment.util.exception.CustomerNotFoundException;
import io.backend.assignment.util.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceUseCase {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public GetOrderResponse createOrder(final Long customerId, final RegisterOrderRequest request) {

        final Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        final List<Cart> carts = cartRepository.findByCustomerId(customerId);

        final Long totalAmount = calculateTotalAmount(carts);

        final Order order = request.toDomain(customer, carts, totalAmount);

        orderRepository.save(order);

        final GetOrderResponse orderResponse = order.toOrderResponse(order);

        return orderResponse;
    }

    @Transactional(readOnly = true)
    public GetOrderResponse getOrder(final Long orderId) {


        final Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        final GetOrderResponse orderResponse = order.toOrderResponse(order);

        return orderResponse;
    }

    private Long totalPrice(Cart cart) {
        return cart.getProduct().getPrice() * cart.getQuantity();
    }

    private Long calculateTotalAmount(List<Cart> carts) {
        return carts.stream()
                .mapToLong(cart -> totalPrice(cart))
                .sum();
    }
}