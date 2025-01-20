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
     * 2. 상품 목록을 조회한다. [O]
     */
    @Test
    @DisplayName("상품을 조회한다.")
    void findProduct() {

        TestScenario.registerProductApi().request();
        assertThat(productService.findAllProductList().size()).isEqualTo(1);
    }
}

