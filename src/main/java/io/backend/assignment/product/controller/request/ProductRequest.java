package io.backend.assignment.product.controller.request;

import io.backend.assignment.product.domain.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProductRequest(
        @NotBlank(message = "상품 이름은 필수입니다.")
        String name,
        String description,
        @NotNull(message = "상품 가격은 필수입니다.")
        Long price,
        @NotNull(message = "수량은 필수입니다.")
        Integer stock,
        @NotNull(message = "생성 시간은 필수입니다.")
        LocalDateTime createdAt,
        @NotNull(message = "수정 시간은 필수입니다.")
        LocalDateTime updatedAt) {

    public Product toDomain(ProductRequest request) {
        return  Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .stock(request.stock())
                .createdAt(request.createdAt())
                .updatedAt(request.updatedAt())
                .build();
    }
}
