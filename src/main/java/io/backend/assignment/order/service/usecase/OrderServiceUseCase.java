package io.backend.assignment.order.service.usecase;

import io.backend.assignment.order.controller.dto.request.RegisterOrderRequest;
import io.backend.assignment.order.controller.dto.response.GetOrderResponse;

public interface OrderServiceUseCase {
    GetOrderResponse createOrder(Long customerId, RegisterOrderRequest registerOrderRequest);
}
