package io.backend.assignment.order;

import static org.assertj.core.api.Assertions.assertThat;

import io.backend.assignment.common.ApiTest;
import io.backend.assignment.common.TestScenario;
import io.backend.assignment.order.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class OrderApiTest extends ApiTest {

    @Autowired private OrderRepository orderRepository;

    @Test
    @DisplayName("주문을 등록한다. [정상케이스]")
    void registerCart() {
        TestScenario.registerOrderApi().register();

        assertThat(orderRepository.findAll().size()).isEqualTo(1);
    }
}
