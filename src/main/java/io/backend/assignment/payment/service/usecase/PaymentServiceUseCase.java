package io.backend.assignment.payment.service.usecase;

import io.backend.assignment.payment.domain.dto.response.PaymentResponse;

public interface PaymentServiceUseCase {

    PaymentResponse payment(Long orderId);
}
