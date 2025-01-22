package io.backend.assignment.common.api;

import io.backend.assignment.common.TestScenario;
import io.backend.assignment.controller.dto.request.CartRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class RegisterCartApi {


    private Long productId = 1L;
    private Long customerId = 1L;
    private int quantity = 3;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public ValidatableResponse register() {

        TestScenario.registerCustomerApi().request();
        TestScenario.registerProductApi().request();

        CartRequest request = new CartRequest(quantity, createdAt, updatedAt);

        final ValidatableResponse response = RestAssured.given().log().all()
                .when()
                .body(request)  // CartRequest 객체를 본문에 넣음
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/carts/{productId}/{customerId}", productId, customerId)
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        return response;
    }
}
