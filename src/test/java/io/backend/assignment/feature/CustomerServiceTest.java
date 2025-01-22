package io.backend.assignment.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

class CustomerServiceTest {


    private RegisterCustomer registerCustomer;
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        registerCustomer = new RegisterCustomer(customerRepository);
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

        private final CustomerRepository customerRepository;

        private RegisterCustomer(final CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        public void register(final CustomerRequest request) {
            final Customer customer = request.toDomain(request);
            customerRepository.save(customer);

        }

        public record CustomerRequest(Long id, String name, LocalDateTime create_at, LocalDateTime update_at) {
            public CustomerRequest {
                Assert.notNull(id, "아이디는 필수입니다.");
                Assert.hasText(name, "고객 이름은 필수입니다.");
                Assert.notNull(create_at, " 생성 시간은 필수입니다.");
                Assert.notNull(update_at, " 수정 시간은 필수입니다.");
            }

            public Customer toDomain(final CustomerRequest request) {
                return new Customer(id(), name(), create_at(), update_at());
            }
        }
    }

    private static class Customer {
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

    private class CustomerRepository {
        private final Map<Customer, Long> customerMap = new HashMap<>();
        private Long sequence = 1L;

        public void save(final Customer customer) {
            customer.assignId(sequence++);
            customerMap.put(customer, sequence);
        }
    }
}
