package io.backend.assignment.feature;

import io.backend.assignment.common.ApiTest;
import io.backend.assignment.domain.ProductRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest extends ApiTest {

    @Autowired
    private ProductRepository productRepository;

    /**
     * 1. 상품을 등록한다. [O]
     * 2. 상품 목록을 조회한다. [O]
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

        RestAssured.given().log().all()
                .when()
                .body(request)
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/products")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        assertThat(productRepository.findAll().size()).isEqualTo(1);

    }

}

