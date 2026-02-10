package com.batyrhan.bankapi.service;

import com.batyrhan.bankapi.dto.CustomerCreateRequest;
import com.batyrhan.bankapi.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse create(CustomerCreateRequest req);
    CustomerResponse getById(int id);
    List<CustomerResponse> getAll();
    void delete(int id);
}