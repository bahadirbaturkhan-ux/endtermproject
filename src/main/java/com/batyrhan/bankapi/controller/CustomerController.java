package com.batyrhan.bankapi.controller;

import com.batyrhan.bankapi.dto.CustomerCreateRequest;
import com.batyrhan.bankapi.dto.CustomerResponse;
import com.batyrhan.bankapi.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerResponse create(@Valid @RequestBody CustomerCreateRequest req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping
    public List<CustomerResponse> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}