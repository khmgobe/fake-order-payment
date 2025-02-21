package io.backend.assignment.product.service;

import io.backend.assignment.product.controller.request.ProductRequest;
import io.backend.assignment.product.domain.Product;
import io.backend.assignment.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Product> findAllProducts() {
        final List<Product> products = productRepository.findAllAvailableProducts();

        return products;
    }
}
