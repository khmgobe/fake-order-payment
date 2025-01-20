package io.backend.assignment.controller;

import io.backend.assignment.controller.dto.request.ProductRequest;
import io.backend.assignment.domain.Product;
import io.backend.assignment.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/v1/products")
    public ResponseEntity<Void> register(@RequestBody @Valid ProductRequest request) {
        productService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/api/v1/products/finds")
    public ResponseEntity<List<Product>> findAllProductList() {
        final List<Product> productList = productService.findAllProductList();

        return ResponseEntity.ok(productList);
    }
}