package io.backend.assignment.customer.service;

import io.backend.assignment.customer.controller.dto.request.CustomerRequest;
import io.backend.assignment.customer.domain.Customer;
import io.backend.assignment.customer.repository.CustomerRepository;
import io.backend.assignment.customer.service.usecase.CustomerServiceUseCase;
import io.backend.assignment.util.exception.CustomerNameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerServiceUseCase {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public void register(final CustomerRequest request) {

        checkIfCustomerNameAlreadyExists(request.name());

        final Customer customer = request.toDomain(request);
        customerRepository.save(customer);
    }

    private void checkIfCustomerNameAlreadyExists(final String customerName) {
        customerRepository
                .findByCustomerName(customerName)
                .ifPresent(
                        customer -> {
                            throw new CustomerNameAlreadyExistsException(customerName);
                        });
    }
}
