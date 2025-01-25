package io.backend.assignment.payment;

import io.backend.assignment.payment.dto.request.RegisterPaymentRequest;
import io.backend.assignment.payment.dto.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PaymentClient {

    private final RestTemplate restTemplate;

    public PaymentResponse processPayment(RegisterPaymentRequest request) {
        final String url = "https://back-end-assignment.free.beeceptor.com/api/v1/payment";
        return restTemplate.postForObject(url, request, PaymentResponse.class);
    }
}