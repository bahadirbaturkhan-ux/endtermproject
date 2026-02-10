package com.batyrhan.bankapi.controller;

import com.batyrhan.bankapi.dto.AccountCreateRequest;
import com.batyrhan.bankapi.dto.AccountResponse;
import com.batyrhan.bankapi.dto.AccountUpdateRequest;
import com.batyrhan.bankapi.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public AccountResponse create(@Valid @RequestBody AccountCreateRequest req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public AccountResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping
    public List<AccountResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/by-customer/{customerId}")
    public List<AccountResponse> byCustomer(@PathVariable int customerId) {
        return service.getByCustomerId(customerId);
    }

    @PutMapping("/{id}/balance")
    public AccountResponse updateBalance(@PathVariable int id, @Valid @RequestBody AccountUpdateRequest req) {
        return service.updateBalance(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}