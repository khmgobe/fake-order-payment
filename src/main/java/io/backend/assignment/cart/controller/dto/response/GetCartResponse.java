package io.backend.assignment.cart.controller.dto.response;

import lombok.Builder;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Builder
public record GetCartResponse(
        Long id,
        Integer quantity,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
        ) {

    public GetCartResponse {
        Assert.notNull(id, "아이디는 필수입니다.");
        Assert.notNull(quantity, "수량은 필수입니다.");
        Assert.notNull(createdAt, "생성 시간은 필수입니다.");
        Assert.notNull(updatedAt, "수정 시간은 필수입니다.");
    }
}
