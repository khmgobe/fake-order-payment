package io.backend.assignment.controller.dto.request;

import io.backend.assignment.domain.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.Assert;

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
        LocalDateTime create_at,
        @NotNull(message = "수정 시간은 필수입니다.")
        LocalDateTime update_at) {

    public ProductRequest {
        Assert.hasText(name, "상품 이름은 필수입니다.");
        Assert.notNull(price, "상품 가격은 필수입니다.");
        Assert.notNull(stock, "수량은 필수입니다.");
        Assert.notNull(create_at, "생성 시간은 필수입니다.");
        Assert.notNull(update_at, "수정 시간은 필수입니다.");
    }

    public Product toDomain(ProductRequest request) {
        return new Product(
                request.name(),
                request.description(),
                request.price(),
                request.stock(),
                request.create_at(),
                request.update_at());
    }
}
