package io.backend.assignment.feature;

import io.backend.assignment.common.ApiTest;
import io.backend.assignment.common.TestScenario;
import io.backend.assignment.customer.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerServiceTest extends ApiTest {


    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("사용자를 등록하고 조회한다. [정상 케이스]")
    void registerCustomer() {

        TestScenario.registerCustomerApi().request();

        assertThat(customerRepository.findAll().size()).isEqualTo(1);
    }
}
