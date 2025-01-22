package io.backend.assignment.util.exception;

public class ProductNotFoundException extends RuntimeException {

    private static final String MESSAGE = "%d번 상품은 존재하지 않습니다.";

    public ProductNotFoundException(final Long productId) {
        super(MESSAGE.formatted(productId));
    }
}
