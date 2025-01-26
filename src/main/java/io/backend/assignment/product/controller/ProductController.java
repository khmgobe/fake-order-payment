package io.backend.assignment.product.controller;

import io.backend.assignment.product.controller.request.ProductRequest;
import io.backend.assignment.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "REGISTER-PRODUCT", description = "상품 등록 API")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 등록")
    @PostMapping("/api/v1/products")
    public ResponseEntity<Void> register(@RequestBody @Valid ProductRequest request) {
        productService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}