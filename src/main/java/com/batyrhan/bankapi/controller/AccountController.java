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

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountResponse create(@Valid @RequestBody AccountCreateRequest req) {
        return accountService.create(req);
    }

    @GetMapping("/{id}")
    public AccountResponse getById(@PathVariable int id) {
        return accountService.getById(id);
    }

    @GetMapping
    public List<AccountResponse> getAll() {
        return accountService.getAll();
    }

    @GetMapping("/customer/{customerId}")
    public List<AccountResponse> getByCustomer(@PathVariable int customerId) {
        return accountService.getByCustomerId(customerId);
    }

    @PutMapping("/{id}/balance")
    public AccountResponse updateBalance(@PathVariable int id, @Valid @RequestBody AccountUpdateRequest req) {
        return accountService.updateBalance(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        accountService.delete(id);
    }
}