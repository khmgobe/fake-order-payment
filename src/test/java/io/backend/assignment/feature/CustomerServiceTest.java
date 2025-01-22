package io.backend.assignment.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

class CustomerServiceTest {


    private RegisterCustomer registerCustomer;

    @BeforeEach
    void setUp() {
        registerCustomer = new RegisterCustomer();
    }

    @Test
    @DisplayName("사용자를 등록하고 조회한다.")
    void registerCustomer()  {

        final Long id = 1L;
        final String name = "name";
        final LocalDateTime create_at = LocalDateTime.now();
        final LocalDateTime update_at = LocalDateTime.now();

        RegisterCustomer.CustomerRequest request = new RegisterCustomer.CustomerRequest(id, name, create_at, update_at);


        registerCustomer.register(request);

    }

    private class RegisterCustomer {

        public void register(final CustomerRequest customerRequest) {

        }

        public record CustomerRequest(Long id, String name, LocalDateTime create_at, LocalDateTime update_at) {
            public CustomerRequest {
                Assert.notNull(id, "아이디는 필수입니다.");
                Assert.hasText(name, "고객 이름은 필수입니다.");
                Assert.notNull(create_at, " 생성 시간은 필수입니다.");
                Assert.notNull(update_at, " 수정 시간은 필수입니다.");
            }
        }
    }
}
