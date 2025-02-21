package io.backend.assignment.payment.domain;

import io.backend.assignment.payment.domain.dto.response.PaymentHistoryResponse;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Entity
@Getter
@Table(name = "payment_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    @Comment("결제 이력 ID (고유 키)")
    private Long id;

    @Column(name = "order_id", nullable = false)
    @Comment("주문 ID")
    private Long orderId;

    @Column(name = "customer_id", nullable = false)
    @Comment("고객 ID")
    private Long customerId;

    @Column(name = "amount", nullable = false)
    @Comment("결제 금액")
    private Long amount;

    @Column(name = "payment_status", nullable = false)
    @Comment("결제 상태")
    private String status;

    @Column(name = "transaction_id")
    @Comment("결제 트랜잭션 ID")
    private String transactionId;

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false,
            columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Comment("생성 시간")
    private LocalDateTime createAt;

    @Builder
    private PaymentHistory(
            final Long orderId,
            final Long customerId,
            final Long amount,
            final String status,
            final String transactionId,
            final LocalDateTime createAt) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.status = status;
        this.transactionId = transactionId;
        this.createAt = createAt;

        Assert.notNull(orderId, "주문 아이디는 필수입니다.");
        Assert.notNull(customerId, "사용자 아이디는 필수입니다.");
        Assert.notNull(amount, "결제 금액은 필수입니다.");
        Assert.notNull(status, "결제 상태는 필수입니다.");
        Assert.notNull(createAt, "생성 시간은 필수입니다.");
    }

    public void changeStatus(final String status) {
        this.status = status;
    }

    public PaymentHistoryResponse toPaymentHistoryResponse(final PaymentHistory paymentHistory) {
        return PaymentHistoryResponse.builder()
                .orderId(paymentHistory.getOrderId())
                .customerId(paymentHistory.getCustomerId())
                .amount(paymentHistory.getAmount())
                .status(paymentHistory.getStatus())
                .transactionId(paymentHistory.getTransactionId())
                .createAt(paymentHistory.getCreateAt())
                .build();
    }
}
