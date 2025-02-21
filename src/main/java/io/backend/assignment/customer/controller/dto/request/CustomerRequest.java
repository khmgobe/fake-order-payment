package io.backend.assignment.customer.controller.dto.request;

import io.backend.assignment.customer.domain.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CustomerRequest(
        @NotBlank(message = "고객 이름은 필수입니다.") String name,
        @NotNull(message = "생성 시간은 필수입니다.") LocalDateTime createdAt,
        @NotNull(message = "수정 시간은 필수입니다.") LocalDateTime updatedAt) {

    public Customer toDomain(final CustomerRequest request) {
        return Customer.builder()
                .name(request.name())
                .createdAt(request.createdAt())
                .updatedAt(request.updatedAt())
                .build();
    }
}
