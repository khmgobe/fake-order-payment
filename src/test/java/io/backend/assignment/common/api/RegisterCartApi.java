package io.backend.assignment.common.api;

import io.backend.assignment.common.TestScenario;
import io.backend.assignment.controller.dto.request.CartRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class RegisterCartApi {

    private Long cartId = 1L;
    private int quantity = 3;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public ValidatableResponse register(Long productId, Long customerId) {

        // 고객과 상품을 미리 등록하는 시나리오 실행
        TestScenario.registerCustomerApi().request();
        TestScenario.registerProductApi().request();

        // CartRequest 객체 생성
        CartRequest request = new CartRequest(quantity, createdAt, updatedAt);

        // API 요청
        final ValidatableResponse response = RestAssured.given().log().all()
                .when()
                .body(request)  // CartRequest 객체를 본문에 넣음
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/carts/" + productId + "/" + customerId)  // URL 경로에 productId와 customerId 포함
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        return response;
    }
}
