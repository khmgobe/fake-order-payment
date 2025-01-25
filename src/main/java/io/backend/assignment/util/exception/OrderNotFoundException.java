package io.backend.assignment.util.exception;

public class OrderNotFoundException extends RuntimeException {

    private static final String MESSAGE = "%d번 주문은 존재하지 않습니다.";

    public OrderNotFoundException(final Long orderId) {
        super(MESSAGE.formatted(orderId));
    }
}
