package io.backend.assignment.payment.domain.dto.response;

public record PaymentResponse(String status, String transactionId, String message) {}
