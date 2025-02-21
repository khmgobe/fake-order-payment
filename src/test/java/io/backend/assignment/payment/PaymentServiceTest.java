package io.backend.assignment.payment;

import static org.assertj.core.api.Assertions.assertThat;

import io.backend.assignment.cart.CartSteps;
import io.backend.assignment.cart.service.CartService;
import io.backend.assignment.customer.CustomerSteps;
import io.backend.assignment.customer.controller.dto.request.CustomerRequest;
import io.backend.assignment.customer.service.CustomerService;
import io.backend.assignment.order.OrderSteps;
import io.backend.assignment.order.controller.dto.request.RegisterOrderRequest;
import io.backend.assignment.order.controller.dto.response.GetOrderResponse;
import io.backend.assignment.order.domain.enumeration.OrderStatus;
import io.backend.assignment.order.service.OrderService;
import io.backend.assignment.payment.domain.dto.response.PaymentHistoryResponse;
import io.backend.assignment.payment.domain.dto.response.PaymentResponse;
import io.backend.assignment.payment.domain.enumeration.PaymentStatus;
import io.backend.assignment.payment.service.PaymentHistoryService;
import io.backend.assignment.payment.service.PaymentService;
import io.backend.assignment.product.ProductSteps;
import io.backend.assignment.product.controller.request.ProductRequest;
import io.backend.assignment.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaymentServiceTest {

    @Autowired private OrderService orderService;

    @Autowired private CartService cartService;

    @Autowired private CustomerService customerService;

    @Autowired private ProductService productService;

    @Autowired private PaymentService paymentService;

    @Autowired private PaymentHistoryService paymentHistoryService;

    @BeforeEach
    void setUp() {
        registerCustomer();
        registerProduct();
        addProductToCart(5);
    }

    @Test
    @DisplayName("주문을 생성하고 결제한다. 결제 이후에는 결제 이력이 남아야 한다.[정상 케이스]")
    void createOrderAndProcessPayment() {

        final Long customerId = 1L;
        final Long orderId = 1L;
        final Long paymentHistoryId = 1L;

        // given : 주문 요청에 필요한 데이터를 만들고 주문을 요청한다.
        final RegisterOrderRequest registerOrderRequest = OrderSteps.orderRequest();
        final GetOrderResponse orderResponse =
                orderService.createOrder(customerId, registerOrderRequest);

        // when : 결제 요청을 처리한다.
        final PaymentResponse paymentResponse = paymentService.payment(orderId);

        // then : 결제가 정상적으로 처리되었는지 확인하고 결제 후 변동된 주문 상태와 결제 이력을 확인한다.
        assertThat(paymentResponse.status()).isEqualTo(PaymentStatus.SUCCESS.name());
        assertThat(paymentResponse.message()).isEqualTo("Payment processed successfully");

        final GetOrderResponse afterOrderResponse = orderService.getOrder(orderId);
        assertThat(afterOrderResponse.orderStatus()).isEqualTo(OrderStatus.COMPLETE);

        final PaymentHistoryResponse paymentHistoryResponse =
                paymentHistoryService.getBy(paymentHistoryId);
        assertThat(paymentHistoryResponse.status()).isEqualTo(PaymentStatus.SUCCESS.name());
        assertThat(paymentHistoryResponse.amount()).isEqualTo(orderResponse.totalAmount());
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
