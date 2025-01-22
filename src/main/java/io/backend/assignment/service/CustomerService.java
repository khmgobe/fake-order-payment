package io.backend.assignment.service;

import io.backend.assignment.controller.dto.request.CustomerRequest;
import io.backend.assignment.domain.Customer;
import io.backend.assignment.repository.CustomerRepository;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void register(final CustomerRequest request) {
        final Customer customer = request.toDomain(request);
        customerRepository.save(customer);
    }
}
