package io.backend.assignment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Comment("상품 테이블")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    @Comment("상품 ID (고유 키)")
    private Long id;

    @Column(name = "name", nullable = false, length = 255, columnDefinition = "VARCHAR(255)")
    @Comment("상품 이름")
    private String name;

    @Column(name = "description", length = 1000, columnDefinition = "VARCHAR(1000)")
    @Comment("상품 설명")
    private String description;

    @Column(name = "price", nullable = false, columnDefinition = "BIGINT")
    @Comment("상품 가격")
    private Long price;

    @Column(name = "stock", nullable = false, columnDefinition = "INT")
    @Comment("재고 수량")
    private Integer stock;

    @Column(name = "create_at", nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Comment("생성 시간")
    LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Comment("수정 시간")
    LocalDateTime updatedAt;

    @Builder
    private Product(
            final String name,
            final String description,
            final Long price,
            final Integer stock,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        validateConstructor(name, price, stock, createdAt, updatedAt);
    }

    private void validateConstructor(
            final String name,
            final Long price,
            final Integer stock,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt) {
        Assert.hasText(name, "상품 이름은 필수입니다.");
        Assert.notNull(price, "상품 가격은 필수입니다.");
        Assert.notNull(stock, "재고 수량은 필수입니다.");
        if(1 > stock) {
            throw new IllegalArgumentException("상품의 재고가 부족합니다.");
        }
        Assert.notNull(createdAt, "생성 시간은 필수입니다.");
        Assert.notNull(updatedAt, "수정 시간은 필수입니다.");
    }
}
