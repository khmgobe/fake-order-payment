package io.backend.assignment.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

public class Customer {

    @Getter
    private Long id;
    private final String name;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    @Builder
    private Customer(final String name, final LocalDateTime create_at, final LocalDateTime update_at) {
        this.name = name;
        this.createAt = create_at;
        this.updateAt = update_at;

        validateConstructor(name, create_at, update_at);
    }

    private void validateConstructor(final String name, final LocalDateTime create_at, final LocalDateTime update_at) {
        Assert.hasText(name, "고객 이름은 필수입니다.");
        Assert.notNull(create_at, " 생성 시간은 필수입니다.");
        Assert.notNull(update_at, " 수정 시간은 필수입니다.");
    }
}
