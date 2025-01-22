package io.backend.assignment.customer.controller;


import io.backend.assignment.customer.controller.dto.request.CustomerRequest;
import io.backend.assignment.customer.service.usecase.CustomerServiceUseCase;
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
public class CustomerController {

    private final CustomerServiceUseCase customerServiceUseCase;

    @Transactional
    @PostMapping("/api/v1/customers")
    public ResponseEntity<Void> register(final @RequestBody @Valid CustomerRequest request) {

        customerServiceUseCase.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
