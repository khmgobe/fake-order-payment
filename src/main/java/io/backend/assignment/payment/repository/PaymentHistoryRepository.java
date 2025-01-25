package io.backend.assignment.payment.repository;

import io.backend.assignment.payment.domain.PaymentHistory;
import io.backend.assignment.util.exception.ProductNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {

    default PaymentHistory getBy(final Long paymentHistoryId) {

            return findById(paymentHistoryId)
                    .orElseThrow(() -> new ProductNotFoundException(paymentHistoryId));
        }
}