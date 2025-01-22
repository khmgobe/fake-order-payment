package io.backend.assignment.feature;

import io.backend.assignment.domain.Customer;
import io.backend.assignment.domain.Product;
import io.backend.assignment.fixture.CustomerFixture;
import io.backend.assignment.fixture.ProductFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    }

    private class RegisterCart {
    }
}
