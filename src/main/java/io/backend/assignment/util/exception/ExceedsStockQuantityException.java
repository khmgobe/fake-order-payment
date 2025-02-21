package io.backend.assignment.util.exception;

public class ExceedsStockQuantityException extends RuntimeException {

    private static final String MESSAGE = "수량이 재고를 초과합니다. 현재 재고: %d, 요청 수량: %d";

    public ExceedsStockQuantityException(final int stock, final int requestedQuantity) {
        super(MESSAGE.formatted(stock, requestedQuantity)); // 재고와 요청 수량을 예외 메시지에 포함
    }
}
