package io.backend.assignment.fixture;

import io.backend.assignment.domain.Product;

import java.time.LocalDateTime;

public class ProductFixture {

    private String name = "productName";
    private String description = "description.";
    private long price = 10000L;
    private int stock = 2;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public static ProductFixture createProduct() {
        return new ProductFixture();
    }

    public Product build() {
        return Product
                .builder()
                .name(name)
                .description(description)
                .price(price)
                .stock(stock)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
