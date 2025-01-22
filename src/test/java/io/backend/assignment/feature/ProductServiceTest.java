package io.backend.assignment.feature;

import io.backend.assignment.common.ApiTest;
import io.backend.assignment.common.TestScenario;
import io.backend.assignment.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest extends ApiTest {

    @Autowired
    private ProductService productService;
    
    /**
     * 1. 상품을 등록한다. [O]
     * 2. 구매 가능한 상품 목록을 조회한다. [O]
     * 2-1. 구매 가능한 -> 재고가 0개 이상인 상품 [O]
     */
    @Test
    @DisplayName("상품을 등록하고 구매 가능한 상품을 조회한다. [정상 케이스]")
    void findAllAvailableProducts() {

        // given : 상품 등록
        TestScenario.registerProductApi().request();

        // when : 구매 가능한 상품의 재고 조회
        int productCount = productService.findAllProducts().size();

        // then : 상품이 정상적으로 조회되어야 한다.
        assertThat(productCount)
                .as("재고가 0개 이상인 상품이 있어야 합니다.")
                .isEqualTo(1);
    }

    @Test
    @DisplayName("상품을 등록하고 재고가 0인 상품을 조회한다. [실패 케이스]")
    void findAllUnAvailableProducts() {

        // given : 재고가 0인 상품 상품 등록
        TestScenario.registerProductApi().stock(0).request();

        // when : 구매 가능한 상품의 재고 조회
        int productCount = productService.findAllProducts().size();

        // then : 상품이 조회되지 않아야 한다.
        assertThat(productCount)
                .as("재고가 0인 상품은 조회되지 않아야 합니다.")
                .isEqualTo(0);
    }
}
