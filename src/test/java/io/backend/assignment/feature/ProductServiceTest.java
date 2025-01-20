package io.backend.assignment.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

class ProductServiceTest {


    private RegisterProduct registerProduct;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        registerProduct = new RegisterProduct(productRepository);
    }

    /**
     * 1. 상품을 등록한다. []
     * 2. 상품 목록을 조회한다. []
     * 3. 상품 목록이 비어있을 경우, 예외를 발생시킨다. []
     */
    @Test
    @DisplayName("상품을 조회한다.")
    void findProduct() {

        final String name = "name";
        final String description = "description";
        final long price = 10000L;
        final int stock = 2;
        final LocalDateTime create_at = LocalDateTime.now();
        final LocalDateTime update_at = LocalDateTime.now();

        RegisterProduct.ProductRequest request = new RegisterProduct.ProductRequest(name, description, price, stock, create_at, update_at);
        registerProduct.register(request);

    }

    private class RegisterProduct {

        private final ProductRepository productRepository;

        private RegisterProduct(final ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        public void register(ProductRequest request) {
            final Product product = request.toDomain(request);
            productRepository.save(product);
        }

        private record ProductRequest(
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

    private static class Product {

        private Long id;
        private final String name;
        private final String description;
        private final Long price;
        private final Integer stock;
        private final LocalDateTime create_at;
        private final LocalDateTime update_at;

        public Product(
                final String name,
                final String description,
                final Long price,
                final Integer stock,
                final LocalDateTime create_at,
                final LocalDateTime update_at) {
            this.name = name;
            this.description = description;
            this.price = price;
            this.stock = stock;
            this.create_at = create_at;
            this.update_at = update_at;
            validateConstructor(name, price, stock, create_at, update_at);
        }

        private void validateConstructor(
                final String name,
                final Long price,
                final Integer stock,
                final LocalDateTime create_at,
                final LocalDateTime update_at) {
            Assert.hasText(name, "상품 이름은 필수입니다.");
            Assert.notNull(price, "상품 가격은 필수입니다.");
            Assert.notNull(stock, "재고 수량은 필수입니다.");
            Assert.notNull(create_at, "생성 시간은 필수입니다.");
            Assert.notNull(update_at, "수정 시간은 필수입니다.");
        }

        public void assignId(final Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }

    private class ProductRepository {
        private final Map<Long, Product> products = new HashMap<>();
        private Long productId = 1L;


        public void save(final Product product) {

            product.assignId(productId++);
            products.put(product.getId(), product);
        }
    }
}

