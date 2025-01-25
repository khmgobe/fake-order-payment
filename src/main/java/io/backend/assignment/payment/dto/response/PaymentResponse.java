package io.backend.assignment.payment.dto.response;

public record PaymentResponse(
        String status,
        String transactionId,
        String message
) {
}
