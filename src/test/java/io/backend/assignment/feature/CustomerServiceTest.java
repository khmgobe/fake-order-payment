package io.backend.assignment.feature;

import io.backend.assignment.controller.dto.request.CustomerRequest;
import io.backend.assignment.repository.CustomerRepository;
import io.backend.assignment.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerServiceTest {


    private CustomerService customerService;
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository = new CustomerRepository();
        customerService = new CustomerService(customerRepository);
    }

    @Test
    @DisplayName("사용자를 등록하고 조회한다.")
    void registerCustomer()  {

        final Long id = 1L;
        final String name = "name";
        final LocalDateTime create_at = LocalDateTime.now();
        final LocalDateTime update_at = LocalDateTime.now();

        CustomerRequest request = new CustomerRequest(id, name, create_at, update_at);

        customerService.register(request);

        assertThat(customerRepository.findAll().size()).isEqualTo(1);

    }
}
