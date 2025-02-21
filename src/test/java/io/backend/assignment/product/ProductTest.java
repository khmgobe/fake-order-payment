package io.backend.assignment.product;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.backend.assignment.product.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    @DisplayName("상품의 재고가 부족한 경우 예외가 발생한다.")
    void validateStockTest() {

        // given : 상품 데이터와 수량을 생성한다.
        final Product product = ProductSteps.createProduct();
        final int stock = -5;

        // when & then: 주문 가능한 상품보다 작은 재고를 등록한다면 예외가 발생한다.
        assertThatThrownBy(() -> product.validationStock(stock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상품의 재고가 부족합니다");
    }
}
