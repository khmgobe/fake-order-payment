package io.backend.assignment.util.exception;

public class PaymentHistoryNotFoundException extends RuntimeException {

    private static final String MESSAGE = "%d번 결제 이력은 존재하지 않습니다.";

    public PaymentHistoryNotFoundException(final Long paymentHistoryId) {
        super(MESSAGE.formatted(paymentHistoryId));
    }
}
