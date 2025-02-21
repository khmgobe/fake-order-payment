package io.backend.assignment.payment.domain.dto.request;

import lombok.Builder;
import org.springframework.util.Assert;

@Builder
public record RegisterPaymentRequest(Long orderId, Long amount) {

    public RegisterPaymentRequest {
        Assert.notNull(orderId, "주문 아이디는 필수입니다.");
        Assert.notNull(amount, "주문 금액은 필수입니다.");
    }

    public static RegisterPaymentRequest createRequest(Long orderId, Long amount) {

        return RegisterPaymentRequest.builder().orderId(orderId).amount(amount).build();
    }
}
