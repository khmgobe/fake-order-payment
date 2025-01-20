package io.backend.assignment.domain;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private final Map<Long, ProductServiceTest.Product> products = new HashMap<>();
    private Long productId = 1L;


    public void save(final ProductServiceTest.Product product) {

        product.assignId(productId++);
        products.put(product.getId(), product);
    }
}
