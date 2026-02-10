package com.batyrhan.bankapi.repository;

import com.batyrhan.bankapi.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    int create(Customer c);
    Optional<Customer> findById(int id);
    List<Customer> findAll();
    void delete(int id);
}