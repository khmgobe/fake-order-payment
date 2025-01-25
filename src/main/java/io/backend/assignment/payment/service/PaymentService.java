package io.backend.assignment.payment.service;

import io.backend.assignment.order.domain.Order;
import io.backend.assignment.order.repository.OrderRepository;
import io.backend.assignment.payment.PaymentClient;
import io.backend.assignment.payment.domain.PaymentHistory;
import io.backend.assignment.payment.domain.dto.request.RegisterPaymentHistoryRequest;
import io.backend.assignment.payment.domain.dto.request.RegisterPaymentRequest;
import io.backend.assignment.payment.domain.dto.response.PaymentResponse;
import io.backend.assignment.payment.domain.enumeration.PaymentStatus;
import io.backend.assignment.payment.repository.PaymentHistoryRepository;
import io.backend.assignment.payment.service.usecase.PaymentServiceUseCase;
import io.backend.assignment.util.exception.PaymentFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentServiceUseCase {

    private final PaymentClient paymentClient;
    private final OrderRepository orderRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;

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

        final PaymentHistory paymentHistory = RegisterPaymentHistoryRequest.toDomain(order, response);

        if (response.status().equals(PaymentStatus.FAILED.name())) {
            paymentHistoryRepository.save(paymentHistory);
            throw new PaymentFailedException(response.message());
        }
        if (response.status().equals(PaymentStatus.SUCCESS.name())) {
            order.confirmOrder();
            paymentHistoryRepository.save(paymentHistory);
        }
    }
}