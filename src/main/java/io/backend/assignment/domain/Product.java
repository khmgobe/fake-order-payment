package io.backend.assignment.domain;

import org.springframework.util.Assert;

import java.time.LocalDateTime;

class Product {

    private Long id;
    private final String name;
    private final String description;
    private final Long price;
    private final Integer stock;
    private final LocalDateTime create_at;
    private final LocalDateTime update_at;

    public Product(
            final String name,
            final String description,
            final Long price,
            final Integer stock,
            final LocalDateTime create_at,
            final LocalDateTime update_at) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.create_at = create_at;
        this.update_at = update_at;
        validateConstructor(name, price, stock, create_at, update_at);
    }

    private void validateConstructor(
            final String name,
            final Long price,
            final Integer stock,
            final LocalDateTime create_at,
            final LocalDateTime update_at) {
        Assert.hasText(name, "상품 이름은 필수입니다.");
        Assert.notNull(price, "상품 가격은 필수입니다.");
        Assert.notNull(stock, "재고 수량은 필수입니다.");
        Assert.notNull(create_at, "생성 시간은 필수입니다.");
        Assert.notNull(update_at, "수정 시간은 필수입니다.");
    }

    public void assignId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
