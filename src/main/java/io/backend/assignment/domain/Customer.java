package io.backend.assignment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Comment("사용자 테이블")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    @Comment("고객 ID (고유 키)")
    private Long id;
    @Column(name = "name", unique = true, columnDefinition = "VARCHAR(50)")
    @Comment("고객 이름")
    private String name;
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Comment("생성 시간")
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Comment("수정 시간")
    private LocalDateTime updatedAt;

    @Builder
    private Customer(final String name, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        validateConstructor(name, createdAt, updatedAt);
    }

    private void validateConstructor(final String name, final LocalDateTime create_at, final LocalDateTime update_at) {
        Assert.hasText(name, "고객 이름은 필수입니다.");
        Assert.notNull(create_at, " 생성 시간은 필수입니다.");
        Assert.notNull(update_at, " 수정 시간은 필수입니다.");
    }
}
