package io.backend.assignment.product;

import io.backend.assignment.common.ApiTest;
import io.backend.assignment.product.controller.request.ProductRequest;

import java.time.LocalDateTime;

public class ProductSteps extends ApiTest {
    
    public static ProductRequest productRequest() {

        final String name = "name";
        final String description ="description";
        final Long price = 15000L;
        final Integer stock = 5;
        final LocalDateTime createdAt = LocalDateTime.now();
        final LocalDateTime updatedAt = LocalDateTime.now();
        
        return new ProductRequest(name, description, price, stock, createdAt, updatedAt);
    }
}
