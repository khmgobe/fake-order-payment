package io.backend.assignment.util.exception;

public class CartNotFoundException extends RuntimeException {

    private static final String MESSAGE = "%d번 장바구니는 존재하지 않습니다.";

    public CartNotFoundException(final Long cartId) {
        super(MESSAGE.formatted(cartId));
    }
}
