package io.backend.assignment.order;

import io.backend.assignment.common.ApiTest;
import io.backend.assignment.order.controller.dto.request.RegisterOrderRequest;
import io.backend.assignment.order.domain.enumeration.OrderStatus;

import java.time.LocalDateTime;

public class OrderSteps extends ApiTest {

    public static RegisterOrderRequest orderRequest() {

        OrderStatus orderStatus = OrderStatus.PENDING;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        return new RegisterOrderRequest(orderStatus, createdAt, updatedAt);
    }
}
