package io.backend.assignment.fixture;

import io.backend.assignment.domain.Customer;

import java.time.LocalDateTime;

public class CustomerFixture {

    private String name = "name";
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();


    public static CustomerFixture createCustomer() {
        return new CustomerFixture();
    }

    public Customer build() {
        return Customer
                .builder()
                .name(name)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
