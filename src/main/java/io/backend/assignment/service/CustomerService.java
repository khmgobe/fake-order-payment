package io.backend.assignment.service;

import io.backend.assignment.controller.dto.request.CustomerRequest;
import io.backend.assignment.domain.Customer;
import io.backend.assignment.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    @PostMapping("/api/v1/customers")
    public ResponseEntity<Void> register(final @RequestBody @Valid CustomerRequest request) {
        final Customer customer = request.toDomain(request);
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
