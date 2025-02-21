package io.backend.assignment.util.exception;

public class CustomerNameAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE = "사용자 이름 %s은 이미 존재합니다.";

    public CustomerNameAlreadyExistsException(String customerName) {
        super(MESSAGE.formatted(customerName));
    }
}
