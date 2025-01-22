package io.backend.assignment.feature;

import io.backend.assignment.domain.Customer;
import io.backend.assignment.domain.Product;
import io.backend.assignment.fixture.CustomerFixture;
import io.backend.assignment.fixture.ProductFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class CartServiceTest {


    private Customer customer;
    private Product product;
    private RegisterCart registerCart;

    @BeforeEach
    void setUp() {
        customer = CustomerFixture.createCustomer().build();
        product = ProductFixture.createProduct().build();
        registerCart = new RegisterCart();
    }

    /**
     * 상품을 장바구니에 추가하기 위해서는,
     */
    @Test
    @DisplayName("상품을 장바구니에 추가한다. [정상 케이스]")
    void addProductToCart() {

        final Long cartId = 1L;
        final Long customerId = customer.getId();
        final Long productId = product.getId();
        final int quantity = 3;
        final LocalDateTime createdAt = LocalDateTime.now();
        final LocalDateTime updatedAt = LocalDateTime.now();

        CartRequest request = new CartRequest(cartId, customerId, productId, quantity, createdAt, updatedAt);

    }

    private class RegisterCart {

    }

    private record CartRequest(
            Long cartId,
            Long customerId,
            Long productId,
            int quantity,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
    }
}
