package io.backend.assignment.payment.service.usecase;

import io.backend.assignment.payment.dto.response.PaymentResponse;

public interface PaymentServiceUseCase {

    public PaymentResponse payment(Long orderId);
}
