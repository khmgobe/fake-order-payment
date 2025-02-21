package io.backend.assignment.customer;

import io.backend.assignment.common.ApiTest;
import io.backend.assignment.customer.controller.dto.request.CustomerRequest;
import java.time.LocalDateTime;

public class CustomerSteps extends ApiTest {

    public static CustomerRequest customerRequest() {

        final String name = "customerName" + System.currentTimeMillis();
        final LocalDateTime createdAt = LocalDateTime.now();
        final LocalDateTime updatedAt = LocalDateTime.now();

        return new CustomerRequest(name, createdAt, updatedAt);
    }
}
