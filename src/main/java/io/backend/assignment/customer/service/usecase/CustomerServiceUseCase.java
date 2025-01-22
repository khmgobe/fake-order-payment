package io.backend.assignment.customer.service.usecase;

import io.backend.assignment.customer.controller.dto.request.CustomerRequest;

public interface CustomerServiceUseCase {

    void register(CustomerRequest request);
}
