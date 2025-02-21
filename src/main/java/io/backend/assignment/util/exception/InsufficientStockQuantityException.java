package io.backend.assignment.util.exception;

public class InsufficientStockQuantityException extends RuntimeException {

    private static final String MESSAGE = "수량은 1개 이상이어야 합니다. 현재 수량: %d";

    public InsufficientStockQuantityException(final Integer quantity) {
        super(MESSAGE.formatted(quantity)); // 현재 수량을 예외 메시지에 포함
    }
}
