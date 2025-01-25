package io.backend.assignment.order;

import io.backend.assignment.cart.CartSteps;
import io.backend.assignment.cart.service.CartService;
import io.backend.assignment.customer.CustomerSteps;
import io.backend.assignment.customer.controller.dto.request.CustomerRequest;
import io.backend.assignment.customer.service.CustomerService;
import io.backend.assignment.order.controller.dto.request.RegisterOrderRequest;
import io.backend.assignment.order.controller.dto.response.GetOrderResponse;
import io.backend.assignment.order.domain.enumeration.OrderStatus;
import io.backend.assignment.order.service.usecase.OrderService;
import io.backend.assignment.product.ProductSteps;
import io.backend.assignment.product.controller.request.ProductRequest;
import io.backend.assignment.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @BeforeEach
    void setUp() {
        // 테스트 전마다 장바구니와 상품 데이터를 초기화
        registerCustomer();
        registerProduct();
        addProductToCart(5);  // 장바구니에 상품을 5개 담음
    }

    @Test
    @DisplayName("주문을 생성한다. [정상 케이스]")
    void createOrder() {
        // given : 고객과 상품을 준비하고 장바구니에 담는다.
        final Long customerId = 1L;

        // when : 주문 요청을 처리한다.
        final RegisterOrderRequest registerOrderRequest = OrderSteps.orderRequest();
        GetOrderResponse orderResponse = orderService.createOrder(customerId, registerOrderRequest);

        // then : 주문이 정상적으로 생성되었는지 확인한다.
        assertThat(orderResponse).isNotNull();
        assertThat(orderResponse.totalAmount()).isEqualTo(75000L);
        assertThat(orderResponse.orderStatus()).isEqualTo(OrderStatus.PENDING);
    }

    private void registerCustomer() {
        final CustomerRequest customerRequest = CustomerSteps.customerRequest();
        customerService.register(customerRequest);
    }

    private void registerProduct() {
        final ProductRequest productRequest = ProductSteps.productRequest();
        productService.register(productRequest);
    }

    private void addProductToCart(final int quantity) {
        final Long productId = 1L;
        final Long customerId = 1L;

        cartService.register(productId, customerId, CartSteps.cartRequest(quantity));
    }
}