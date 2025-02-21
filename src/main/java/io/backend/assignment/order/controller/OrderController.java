package io.backend.assignment.order.controller;

import io.backend.assignment.order.controller.dto.request.RegisterOrderRequest;
import io.backend.assignment.order.controller.dto.response.GetOrderResponse;
import io.backend.assignment.order.service.usecase.OrderServiceUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CREATE-ORDER", description = "주문 생성 API")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceUseCase orderServiceUseCase;

    @Operation(summary = "주문 생성")
    @PostMapping("/api/v1/orders/{customerId}")
    public ResponseEntity<GetOrderResponse> createOrder(
            @PathVariable final Long customerId,
            @RequestBody @Valid RegisterOrderRequest registerOrderRequest) {

        orderServiceUseCase.createOrder(customerId, registerOrderRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
