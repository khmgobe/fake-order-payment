package io.backend.assignment.feature;

import io.backend.assignment.domain.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest {

    private RegisterProduct registerProduct;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        registerProduct = new RegisterProduct(productRepository);
    }

    /**
     * 1. 상품을 등록한다. []
     * 2. 상품 목록을 조회한다. []
     * 3. 상품 목록이 비어있을 경우, 예외를 발생시킨다. []
     */
    @Test
    @DisplayName("상품을 조회한다.")
    void findProduct() {

        final String name = "name";
        final String description = "description";
        final long price = 10000L;
        final int stock = 2;
        final LocalDateTime create_at = LocalDateTime.now();
        final LocalDateTime update_at = LocalDateTime.now();

        RegisterProduct.ProductRequest request = new RegisterProduct.ProductRequest(name, description, price, stock, create_at, update_at);
        registerProduct.register(request);

        assertThat(productRepository.findAll().size()).isEqualTo(1);

    }

}

