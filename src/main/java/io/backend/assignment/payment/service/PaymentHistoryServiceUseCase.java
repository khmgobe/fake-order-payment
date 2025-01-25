package io.backend.assignment.payment.service;

import io.backend.assignment.payment.domain.dto.response.PaymentHistoryResponse;

public interface PaymentHistoryServiceUseCase {

    PaymentHistoryResponse getBy(final Long paymentHistoryId);
}
