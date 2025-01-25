package io.backend.assignment.util.exception;

public class PaymentFailedException extends RuntimeException {

    public PaymentFailedException(final String message) {
        super(message);
    }
}
