package io.backend.assignment.domain;

import org.springframework.util.Assert;

import java.time.LocalDateTime;

public class Customer {
    private Long id;
    private final String name;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    public Long getId() {
        return id;
    }

    public Customer(final Long id, final String name, final LocalDateTime create_at, final LocalDateTime update_at) {
        this.id = id;
        this.name = name;
        this.createAt = create_at;
        this.updateAt = update_at;

        validateConstructor(id, name, create_at, update_at);
    }

    private void validateConstructor(final Long id, final String name, final LocalDateTime create_at, final LocalDateTime update_at) {
        Assert.notNull(id, "아이디는 필수입니다.");
        Assert.hasText(name, "고객 이름은 필수입니다.");
        Assert.notNull(create_at, " 생성 시간은 필수입니다.");
        Assert.notNull(update_at, " 수정 시간은 필수입니다.");
    }

    public void assignId(final Long id) {
        this.id = id;
    }
}
