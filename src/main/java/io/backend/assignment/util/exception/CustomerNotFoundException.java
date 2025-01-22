package io.backend.assignment.util.exception;

public class CustomerNotFoundException extends RuntimeException {

    private static final String MESSAGE = "%d번 사용자는 존재하지 않습니다.";

    public CustomerNotFoundException(final Long customerId) {
        super(MESSAGE.formatted(customerId));
    }
}
