package io.backend.assignment.payment;

import io.backend.assignment.cart.CartSteps;
import io.backend.assignment.cart.service.CartService;
import io.backend.assignment.customer.CustomerSteps;
import io.backend.assignment.customer.controller.dto.request.CustomerRequest;
import io.backend.assignment.customer.service.CustomerService;
import io.backend.assignment.order.OrderSteps;
import io.backend.assignment.order.controller.dto.request.RegisterOrderRequest;
import io.backend.assignment.order.controller.dto.response.GetOrderResponse;
import io.backend.assignment.order.domain.enumeration.OrderStatus;
import io.backend.assignment.order.service.usecase.OrderService;
import io.backend.assignment.payment.dto.response.PaymentResponse;
import io.backend.assignment.payment.enumeration.PaymentStatus;
import io.backend.assignment.payment.service.PaymentService;
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
class PaymentServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        registerCustomer();
        registerProduct();
        addProductToCart(5);
    }

    @Test
    @DisplayName("주문을 생성하고 결제를 처리한다. [정상 케이스]")
    void createOrderAndProcessPayment() {

        final Long customerId = 1L;
        final Long orderId = 1L;

        // given : 주문 요청에 필요한 데이터를 만들고 주문을 요청한다.
        final RegisterOrderRequest registerOrderRequest = OrderSteps.orderRequest();
        final GetOrderResponse orderResponse = orderService.createOrder(customerId, registerOrderRequest);

        // when : 결제 요청을 처리한다.
        final PaymentResponse paymentResponse = paymentService.payment(orderId);

        // then : 결제가 정상적으로 처리되었는지 확인하고 결제 후 변동된 주문 상태를 확인한다.
        assertThat(paymentResponse.status()).isEqualTo(PaymentStatus.SUCCESS.name());
        assertThat(paymentResponse.message()).isEqualTo("Payment processed successfully");

        final GetOrderResponse afterOrderResponse = orderService.getOrder(orderId);
        assertThat(afterOrderResponse.orderStatus()).isEqualTo(OrderStatus.COMPLETE);
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