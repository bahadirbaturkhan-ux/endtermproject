package com.batyrhan.bankapi.service.impl;

import com.batyrhan.bankapi.dto.CustomerCreateRequest;
import com.batyrhan.bankapi.dto.CustomerResponse;
import com.batyrhan.bankapi.exception.DuplicateResourceException;
import com.batyrhan.bankapi.exception.ResourceNotFoundException;
import com.batyrhan.bankapi.model.Customer;
import com.batyrhan.bankapi.patterns.builder.CustomerBuilder;
import com.batyrhan.bankapi.repository.CustomerRepository;
import com.batyrhan.bankapi.utils.ReflectionUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements com.batyrhan.bankapi.service.CustomerService {

    private final CustomerRepository repo;

    public CustomerServiceImpl(CustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    public CustomerResponse create(CustomerCreateRequest req) {
        try {
            Customer c = new CustomerBuilder().id(null).name(req.name).email(req.email).build();
            int id = repo.create(c);
            ReflectionUtils.inspect(c);
            return new CustomerResponse(id, req.name, req.email);
        } catch (DuplicateKeyException e) {
            throw new DuplicateResourceException("Email already exists");
        }
    }

    @Override
    public CustomerResponse getById(int id) {
        Customer c = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return new CustomerResponse(c.getId(), c.getName(), c.getEmail());
    }

    @Override
    public List<CustomerResponse> getAll() {
        return repo.findAll().stream()
                .map(c -> new CustomerResponse(c.getId(), c.getName(), c.getEmail()))
                .toList();
    }

    @Override
    public void delete(int id) {
        repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        repo.delete(id);
    }
}