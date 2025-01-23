package io.backend.assignment.cart;

import io.backend.assignment.cart.controller.dto.request.UpdateCartRequest;
import io.backend.assignment.cart.controller.dto.response.GetCartResponse;
import io.backend.assignment.cart.service.CartService;
import io.backend.assignment.customer.CustomerSteps;
import io.backend.assignment.customer.controller.dto.request.CustomerRequest;
import io.backend.assignment.customer.service.CustomerService;
import io.backend.assignment.product.ProductSteps;
import io.backend.assignment.product.controller.request.ProductRequest;
import io.backend.assignment.product.service.ProductService;
import io.backend.assignment.util.exception.InvalidQuantityException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;


    @Test
    @DisplayName("장바구니에 상품을 등록한다.")
    void registerCart()  {

        // given : 장바구니 아이디를 생성한다.
        final Long cartId = 1L;

        // when : 장바구니에 상품을 등록한다.
        addProductToCart();

        // then : 정상적으로 장바구니에 상품이 등록되었는지 확인.
        final GetCartResponse cartResponse = cartService.getCart(cartId);

        assertThat(cartResponse.quantity()).isEqualTo(3);

    }

    @Test
    @DisplayName("장바구니의 수량을 수정한다. [정상 케이스]")
    void changeQuantityCart() {

        // given: 장바구니 아이디를 생성하고 장바구니에 상품을 등록한다.
        addProductToCart();

        final Long cartId = 1L;

        // when : 장바구니 수정 데이터 생성, 장바구니 내 수량 수정 시도
        final UpdateCartRequest updateCartRequest = CartSteps.updateCartRequest(5);
        cartService.updateCart(cartId, updateCartRequest);

        // then : 정상적으로 장바구니 내 수량이 변경되었는지 확인한다.
        final GetCartResponse cartResponse = cartService.getCart(cartId);
        assertThat(cartResponse.quantity()).isEqualTo(8);
    }

    @Test
    @DisplayName("장바구니에 존재하던 수량보다 작은 수량으로 수정한다. [실패 케이스]")
    void changeNotEnoughQuantityCart() {

        // given: 장바구니 아이디를 생성하고 장바구니에 상품 등록
        final Long cartId = 1L;
        addProductToCart();

        // when, then: 상품 수정 데이터 생성, 상품 수정 시도 시 예외가 발생하여야 한다.
        final UpdateCartRequest request = CartSteps.updateCartRequest(-10);
        assertThatThrownBy(() -> cartService.updateCart(cartId, request))
                .isInstanceOf(InvalidQuantityException.class)
                .hasMessageContaining("수량은 1개 이상이어야 합니다");
    }

    private void registerCustomer() {
        final CustomerRequest customerRequest = CustomerSteps.customerRequest();
        customerService.register(customerRequest);
    }

    private void registerProduct() {
        final ProductRequest productRequest = ProductSteps.productRequest();
        productService.register(productRequest);
    }

    private void addProductToCart() {
        final Long productId = 1L;
        final Long customerId = 1L;

        registerProduct();
        registerCustomer();

        cartService.register(productId, customerId, CartSteps.cartRequest());
    }
}
