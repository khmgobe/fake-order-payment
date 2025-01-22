package io.backend.assignment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Entity
@Table(name = "cart")
@Comment("장바구니")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity", nullable = false)
    @Comment("상품 수량")
    private Integer quantity;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Comment("생성 시간")
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Comment("수정 시간")
    private LocalDateTime updatedAt;

    @Builder
    private Cart(final Customer customer,
                 final Product product,
                 final Integer quantity,
                 final LocalDateTime createdAt,
                 final LocalDateTime updatedAt) {

        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        validateConstructor(customer, product, quantity, createdAt, updatedAt);
    }

    private void validateConstructor(final Customer customer, final Product product, final Integer quantity, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        Assert.notNull(customer, "사용자는 필수입니다.");
        Assert.notNull(product, "상품은 필수입니다.");
        Assert.notNull(quantity, "수량은 필수입니다.");
        if (1 > quantity) {
            throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
        }
        Assert.notNull(createdAt, "생성 시간은 필수입니다.");
        Assert.notNull(updatedAt, "수정 시간은 필수입니다.");
    }

}
