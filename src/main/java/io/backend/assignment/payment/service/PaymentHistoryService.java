package io.backend.assignment.payment.service;

import io.backend.assignment.payment.domain.PaymentHistory;
import io.backend.assignment.payment.domain.dto.response.PaymentHistoryResponse;
import io.backend.assignment.payment.repository.PaymentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService implements PaymentHistoryServiceUseCase {

    private final PaymentHistoryRepository paymentHistoryRepository;

    @Override
    public PaymentHistoryResponse getBy(final Long paymentHistoryId) {
        final PaymentHistory paymentHistory = paymentHistoryRepository.getBy(paymentHistoryId);
        final PaymentHistoryResponse paymentHistoryResponse =
                paymentHistory.toPaymentHistoryResponse(paymentHistory);
        return paymentHistoryResponse;
    }
}
