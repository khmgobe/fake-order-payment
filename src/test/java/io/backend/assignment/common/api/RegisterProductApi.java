package io.backend.assignment.common.api;

import io.backend.assignment.controller.dto.request.ProductRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class RegisterProductApi {

    private String name = "name";
    private String description = "description";
    private long price = 10000L;
    private int stock = 2;
    private LocalDateTime create_at = LocalDateTime.now();
    private LocalDateTime update_at = LocalDateTime.now();

    public RegisterProductApi stock (final int stock) {
        this.stock = stock;
        return this;
    }

    public ValidatableResponse request() {

        ProductRequest request = new ProductRequest(name, description, price, stock, create_at, update_at);

        final ValidatableResponse response = RestAssured.given().log().all()
                .when()
                .body(request)
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/products")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());
        return response;
    }
}
