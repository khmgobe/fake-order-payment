package io.backend.assignment.customer.repository;

import io.backend.assignment.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c where c.name = :name")
    Optional<Customer> findByCustomerName(String name);
}
