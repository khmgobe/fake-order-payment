package io.backend.assignment.service;

import io.backend.assignment.controller.dto.request.ProductRequest;
import io.backend.assignment.domain.Product;
import io.backend.assignment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void register(ProductRequest request) {
        final Product product = request.toDomain(request);
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProductList() {
        final List<Product> products = productRepository.findAll();

        return products;
    }
}