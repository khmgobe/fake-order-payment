package io.backend.assignment.product.repository;

import io.backend.assignment.product.domain.Product;
import io.backend.assignment.util.exception.ProductNotFoundException;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.stock > 0")
    List<Product> findAllAvailableProducts();

    default Product getBy(final Long productId) {
        return findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }
}
