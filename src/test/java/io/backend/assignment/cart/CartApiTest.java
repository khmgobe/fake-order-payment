package io.backend.assignment.cart;

import io.backend.assignment.cart.repository.CartRepository;
import io.backend.assignment.common.ApiTest;
import io.backend.assignment.common.TestScenario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class CartApiTest extends ApiTest {

    @Autowired
    private CartRepository cartRepository;

    @Test
    @DisplayName("장바구니에 상품을 등록한다 [정상케이스]")
    void registerCart()  {
        TestScenario.registerCartApi().register();

        assertThat(cartRepository.findAll().size()).isEqualTo(1);
    }
}
