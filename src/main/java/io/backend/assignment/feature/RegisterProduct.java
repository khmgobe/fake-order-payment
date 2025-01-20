package io.backend.assignment.feature;

import io.backend.assignment.domain.Product;
import io.backend.assignment.domain.ProductRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
class RegisterProduct {

    private final ProductRepository productRepository;

    @Transactional
    @PostMapping("/api/v1/products")
    public ResponseEntity<Void> register(@RequestBody @Valid ProductRequest request) {
        final Product product = request.toDomain(request);
        productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    record ProductRequest(
            @NotBlank(message = "상품 이름은 필수입니다.")
            String name,
            String description,
            @NotNull(message = "상품 가격은 필수입니다.")
            Long price,
            @NotNull(message = "수량은 필수입니다.")
            Integer stock,
            @NotNull(message = "생성 시간은 필수입니다.")
            LocalDateTime create_at,
            @NotNull(message = "수정 시간은 필수입니다.")
            LocalDateTime update_at) {

        ProductRequest {
            Assert.hasText(name, "상품 이름은 필수입니다.");
            Assert.notNull(price, "상품 가격은 필수입니다.");
            Assert.notNull(stock, "수량은 필수입니다.");
            Assert.notNull(create_at, "생성 시간은 필수입니다.");
            Assert.notNull(update_at, "수정 시간은 필수입니다.");
        }

        public Product toDomain(ProductRequest request) {
            return new Product(
                    request.name(),
                    request.description(),
                    request.price(),
                    request.stock(),
                    request.create_at(),
                    request.update_at());
        }
    }
}
