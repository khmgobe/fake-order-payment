package io.backend.assignment.order.domain;

import io.backend.assignment.cart.controller.dto.response.GetCartResponse;
import io.backend.assignment.cart.domain.Cart;
import io.backend.assignment.customer.domain.Customer;
import io.backend.assignment.order.controller.dto.response.GetOrderResponse;
import io.backend.assignment.order.domain.enumeration.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
@Comment("주문")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<Cart> carts;

    @Column(name = "total_amount", nullable = false)
    @Comment("총 주문 금액")
    private Long totalAmount;

    @Column(name = "order_status", nullable = false)
    @Comment("주문 상태")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Comment("주문 생성 시간")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Comment("주문 수정 시간")
    private LocalDateTime updatedAt;

    @Builder
    private Order(final Customer customer,
                  final List<Cart> carts,
                  final Long totalAmount,
                  final OrderStatus orderStatus,
                  final LocalDateTime createdAt,
                  final LocalDateTime updatedAt) {

        this.customer = customer;
        this.carts = carts;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.totalAmount = totalAmount;

        validateConstructor(customer, carts, totalAmount, orderStatus, createdAt, updatedAt);
    }

    private void validateConstructor(final Customer customer,
                                     final List<Cart> carts,
                                     final Long totalAmount,
                                     final OrderStatus orderStatus,
                                     final LocalDateTime createdAt,
                                     final LocalDateTime updatedAt) {
        Assert.notNull(customer, "사용자는 필수입니다.");
        Assert.notNull(carts, "장바구니 항목은 필수입니다.");
        Assert.notNull(totalAmount, "총 결제 금액은 필수입니다.");
        Assert.notNull(orderStatus, "주문 상태는 필수입니다.");
        Assert.notNull(createdAt, "주문 생성 시간은 필수입니다.");
        Assert.notNull(updatedAt, "주문 수정 시간은 필수입니다.");
    }

    public GetOrderResponse toOrderResponse(final Order order) {
        return GetOrderResponse
                .builder()
                .totalAmount(order.getTotalAmount())
                .orderStatus(order.getOrderStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    public void confirmOrder() {
        if(orderStatus.equals(OrderStatus.PENDING)) {
            orderStatus = OrderStatus.COMPLETE;
        }
    }

    private static Long totalPrice(Cart cart) {
        return cart.getProduct().getPrice() * cart.getQuantity();
    }

    public static Long calculateTotalAmount(List<Cart> carts) {
        return carts.stream()
                .mapToLong(cart -> totalPrice(cart))
                .sum();
    }
}