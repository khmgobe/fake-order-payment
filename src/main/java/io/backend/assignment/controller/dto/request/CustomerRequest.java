package io.backend.assignment.controller.dto.request;

import io.backend.assignment.domain.Customer;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

public record CustomerRequest(String name, LocalDateTime create_at, LocalDateTime update_at) {
    public CustomerRequest {
        Assert.hasText(name, "고객 이름은 필수입니다.");
        Assert.notNull(create_at, " 생성 시간은 필수입니다.");
        Assert.notNull(update_at, " 수정 시간은 필수입니다.");
    }

    public Customer toDomain(final CustomerRequest request) {
        return Customer.builder()
                .name(request.name())
                .create_at(request.create_at())
                .update_at(request.update_at())
                .build();
    }
}
