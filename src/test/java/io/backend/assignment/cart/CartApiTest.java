package io.backend.assignment.cart;

import static org.assertj.core.api.Assertions.assertThat;

import io.backend.assignment.cart.controller.dto.request.UpdateCartRequest;
import io.backend.assignment.cart.repository.CartRepository;
import io.backend.assignment.common.ApiTest;
import io.backend.assignment.common.TestScenario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

class CartApiTest extends ApiTest {

    @Autowired private CartRepository cartRepository;

    @Test
    @DisplayName("장바구니에 상품을 등록한다 [정상케이스]")
    void registerCart() {
        TestScenario.registerCartApi().register();

        assertThat(cartRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("장바구니에 담은 상품을 수정한다 [정상 케이스]")
    void updateCart() {
        TestScenario.registerCartApi().register();
        final Long cartId = 1L;
        final Integer quantity = 5;

        final UpdateCartRequest request = CartSteps.updateCartRequest(quantity);

        final ExtractableResponse<Response> response =
                RestAssured.given()
                        .log()
                        .all()
                        .when()
                        .body(request) // CartRequest 객체를 본문에 넣음
                        .contentType(ContentType.JSON)
                        .when()
                        .patch("/api/v1/carts/{cartId}", cartId)
                        .then()
                        .log()
                        .all()
                        .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(cartRepository.findById(cartId).get().getQuantity()).isEqualTo(quantity);
    }

    @Test
    @DisplayName("장바구니를 삭제한다.")
    void deleteCart() {

        TestScenario.registerCartApi().register();
        final Long cartId = 1L;

        final ExtractableResponse<Response> response =
                RestAssured.given()
                        .log()
                        .all()
                        .when()
                        .contentType(ContentType.JSON)
                        .when()
                        .delete("/api/v1/carts/{cartId}", cartId)
                        .then()
                        .log()
                        .all()
                        .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(cartRepository.findById(cartId)).isEmpty();
    }
}
