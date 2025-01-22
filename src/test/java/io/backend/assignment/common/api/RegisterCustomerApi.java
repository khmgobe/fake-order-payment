package io.backend.assignment.common.api;

import io.backend.assignment.controller.dto.request.CustomerRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class RegisterCustomerApi {


    private Long id = 1L;
    private String name = "name";
    private LocalDateTime create_at = LocalDateTime.now();
    private LocalDateTime update_at = LocalDateTime.now();

    public ValidatableResponse request() {

        CustomerRequest request = new CustomerRequest(id, name, create_at, update_at);

        final ValidatableResponse response = RestAssured.given().log().all()
                .when()
                .body(request)
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/customers")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        return response;
    }
}
