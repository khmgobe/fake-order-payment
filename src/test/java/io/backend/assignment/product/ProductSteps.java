package io.backend.assignment.product;

import io.backend.assignment.common.ApiTest;
import io.backend.assignment.product.controller.request.ProductRequest;
import io.backend.assignment.product.domain.Product;

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

    public static Product createProduct() {

        final String name = "product_name";
        final String description ="product_description";
        final Long price = 15000L;
        final Integer stock = 10;
        final LocalDateTime createdAt = LocalDateTime.now();
        final LocalDateTime updatedAt = LocalDateTime.now();

        final Product product = Product
                .builder()
                .name(name)
                .description(description)
                .price(price)
                .stock(stock)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

        return product;
    }
}
