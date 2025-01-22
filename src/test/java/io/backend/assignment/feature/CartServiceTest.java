package io.backend.assignment.feature;

import io.backend.assignment.common.ApiTest;
import io.backend.assignment.common.TestScenario;
import io.backend.assignment.repository.CartRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.assertj.core.api.Assertions.assertThat;

class CartServiceTest extends ApiTest {

    @Autowired
    private CartRepository cartRepository;

    @Test
    @DisplayName("상품을 장바구니에 추가한다. [정상 케이스]")
    void addProductToCart() {

        TestScenario.registerCartApi().register();

        assertThat(cartRepository.findAll().size()).isEqualTo(1);

    }

}
