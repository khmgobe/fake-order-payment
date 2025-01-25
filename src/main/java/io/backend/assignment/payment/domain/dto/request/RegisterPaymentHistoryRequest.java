package io.backend.assignment.payment.domain.dto.request;

import io.backend.assignment.order.domain.Order;
import io.backend.assignment.payment.domain.PaymentHistory;
import io.backend.assignment.payment.domain.dto.response.PaymentResponse;

import java.time.LocalDateTime;

public record RegisterPaymentHistoryRequest() {

    public static PaymentHistory toDomain(final Order order, final PaymentResponse response) {
        final PaymentHistory paymentHistory = PaymentHistory
                .builder()
                .orderId(order.getId())
                .customerId(order.getCustomer().getId())
                .amount(order.getTotalAmount())
                .status(response.status())
                .transactionId(response.transactionId())
                .createAt(LocalDateTime.now())
                .build();

        return paymentHistory;
    }
}