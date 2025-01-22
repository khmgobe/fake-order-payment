package io.backend.assignment.common.api;

import io.backend.assignment.common.TestScenario;
import io.backend.assignment.controller.dto.request.CustomerRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class RegisterCustomerApi {


    private String name = "test_customer";
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public RegisterCustomerApi name(String name) {
        this.name = name;
        return this;
    }

    public TestScenario request() {

        CustomerRequest request = new CustomerRequest(name, createdAt, updatedAt);

        final ValidatableResponse response = RestAssured.given().log().all()
                .when()
                .body(request)
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/customers")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        return new TestScenario();
    }
}
