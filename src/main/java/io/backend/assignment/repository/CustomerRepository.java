package io.backend.assignment.repository;

import io.backend.assignment.domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository {
    private final Map<Long, Customer> customerMap = new HashMap<>();
    private Long sequence = 1L;

    public void save(final Customer customer) {
        customer.assignId(sequence++);
        customerMap.put(sequence, customer);
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }
}
