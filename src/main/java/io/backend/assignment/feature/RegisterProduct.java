package io.backend.assignment.feature;

import io.backend.assignment.domain.Product;
import io.backend.assignment.domain.ProductRepository;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

class RegisterProduct {

    private final ProductRepository productRepository;

    RegisterProduct(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void register(ProductRequest request) {
        final Product product = request.toDomain(request);
        productRepository.save(product);
    }

    record ProductRequest(
            String name,
            String description,
            Long price,
            Integer stock,
            LocalDateTime create_at,
            LocalDateTime update_at) {

        ProductRequest {
            Assert.hasText(name, "이름은 필수입니다.");
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
