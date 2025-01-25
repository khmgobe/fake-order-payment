package io.backend.assignment.payment.service;

import io.backend.assignment.order.domain.Order;
import io.backend.assignment.order.repository.OrderRepository;
import io.backend.assignment.payment.PaymentClient;
import io.backend.assignment.payment.dto.request.RegisterPaymentRequest;
import io.backend.assignment.payment.dto.response.PaymentResponse;
import io.backend.assignment.payment.enumeration.PaymentStatus;
import io.backend.assignment.payment.service.usecase.PaymentServiceUseCase;
import io.backend.assignment.util.exception.OrderNotFoundException;
import io.backend.assignment.util.exception.PaymentFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentServiceUseCase {

    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;

    @Override
    public PaymentResponse payment(final Long orderId) {

        final Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        final RegisterPaymentRequest request = RegisterPaymentRequest.createRequest(orderId, order.getTotalAmount());

        final PaymentResponse paymentResponse = paymentClient.processPayment(request);

        if (paymentResponse.status().equals(PaymentStatus.FAILED.name())) {
            throw new PaymentFailedException(paymentResponse.message());
        }

        if (paymentResponse.status().equals(PaymentStatus.SUCCESS.name())) {
            order.confirmOrder();
            orderRepository.save(order);
        }

        return paymentResponse;
    }
}