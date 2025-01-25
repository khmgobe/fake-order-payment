package io.backend.assignment.payment.service;

import io.backend.assignment.order.domain.Order;
import io.backend.assignment.order.repository.OrderRepository;
import io.backend.assignment.payment.PaymentClient;
import io.backend.assignment.payment.dto.request.RegisterPaymentRequest;
import io.backend.assignment.payment.dto.response.PaymentResponse;
import io.backend.assignment.payment.enumeration.PaymentStatus;
import io.backend.assignment.payment.service.usecase.PaymentServiceUseCase;
import io.backend.assignment.util.exception.PaymentFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentServiceUseCase {

    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;

    @Override
    @Transactional
    public PaymentResponse payment(final Long orderId) {

        final Order order = orderRepository.getBy(orderId);

        final RegisterPaymentRequest request = RegisterPaymentRequest.createRequest(orderId, order.getTotalAmount());

        final PaymentResponse paymentResponse = paymentClient.processPayment(request);

        processPaymentResult(order, paymentResponse);

        return paymentResponse;

    }

    private void processPaymentResult(final Order order, final PaymentResponse response) {
        if (response.status().equals(PaymentStatus.FAILED.name())) {
            throw new PaymentFailedException(response.message());
        }
        if (response.status().equals(PaymentStatus.SUCCESS.name())) {
            order.confirmOrder();
        }
    }
}