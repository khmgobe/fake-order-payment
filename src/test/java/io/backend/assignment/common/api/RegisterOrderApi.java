package io.backend.assignment.common.api;

import io.backend.assignment.common.TestScenario;
import io.backend.assignment.order.controller.dto.request.RegisterOrderRequest;
import io.backend.assignment.order.domain.enumeration.OrderStatus;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class RegisterOrderApi {

    private Long customerId = 1L;
    private OrderStatus orderStatus = OrderStatus.PENDING;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public ValidatableResponse register() {

        TestScenario.registerCartApi().register();

        final RegisterOrderRequest request =
                new RegisterOrderRequest(orderStatus, createdAt, updatedAt);

        final ValidatableResponse response =
                RestAssured.given()
                        .log()
                        .all()
                        .when()
                        .body(request)
                        .contentType(ContentType.JSON)
                        .when()
                        .post("/api/v1/orders/{customerId}", customerId)
                        .then()
                        .log()
                        .all()
                        .statusCode(HttpStatus.CREATED.value());

        return response;
    }
}
