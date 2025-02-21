package io.backend.assignment.payment.domain.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import org.springframework.util.Assert;

@Builder
public record PaymentHistoryResponse(
        Long orderId,
        Long customerId,
        Long amount,
        String status,
        String transactionId,
        LocalDateTime createAt) {

    public PaymentHistoryResponse {
        Assert.notNull(orderId, "주문 아이디는 필수입니다.");
        Assert.notNull(customerId, "사용자 아이디는 필수입니다.");
        Assert.notNull(amount, "결제 금액은 필수입니다.");
        Assert.notNull(status, "결제 상태는 필수입니다.");
        Assert.notNull(createAt, "생성 시간은 필수입니다.");
    }
}
