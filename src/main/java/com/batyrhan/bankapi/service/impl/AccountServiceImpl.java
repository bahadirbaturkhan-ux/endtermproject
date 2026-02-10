package com.batyrhan.bankapi.service.impl;

import com.batyrhan.bankapi.dto.AccountCreateRequest;
import com.batyrhan.bankapi.dto.AccountResponse;
import com.batyrhan.bankapi.dto.AccountUpdateRequest;
import com.batyrhan.bankapi.exception.DuplicateResourceException;
import com.batyrhan.bankapi.exception.ResourceNotFoundException;
import com.batyrhan.bankapi.model.AccountBase;
import com.batyrhan.bankapi.patterns.builder.AccountBuilder;
import com.batyrhan.bankapi.repository.AccountRepository;
import com.batyrhan.bankapi.repository.CustomerRepository;
import com.batyrhan.bankapi.utils.SortingUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements com.batyrhan.bankapi.service.AccountService {

    private final AccountRepository repo;
    private final CustomerRepository customerRepo;

    public AccountServiceImpl(AccountRepository repo, CustomerRepository customerRepo) {
        this.repo = repo;
        this.customerRepo = customerRepo;
    }

    private AccountResponse toResponse(AccountBase a) {
        return new AccountResponse.Builder()
                .id(a.getId())
                .accountNumber(a.getAccountNumber())
                .balance(a.getBalance())
                .type(a.getAccountType())
                .customerId(a.getCustomerId())
                .build();
    }

    @Override
    public AccountResponse create(AccountCreateRequest req) {
        customerRepo.findById(req.customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        try {
            AccountBase a = new AccountBuilder()
                    .id(null)
                    .accountNumber(req.accountNumber)
                    .balance(req.balance)
                    .type(req.type)
                    .customerId(req.customerId)
                    .build();

            int id = repo.create(a);

            AccountBase created = new AccountBuilder()
                    .id(id)
                    .accountNumber(req.accountNumber)
                    .balance(req.balance)
                    .type(req.type)
                    .customerId(req.customerId)
                    .build();

            return toResponse(created);

        } catch (DuplicateKeyException e) {
            throw new DuplicateResourceException("Account number already exists");
        }
    }

    @Override
    public AccountResponse getById(int id) {
        AccountBase a = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        return toResponse(a);
    }

    @Override
    public List<AccountResponse> getAll() {
        List<AccountBase> list = repo.findAll();
        list = SortingUtils.byBalanceAsc(list);
        return list.stream().map(this::toResponse).toList();
    }

    @Override
    public List<AccountResponse> getByCustomerId(int customerId) {
        customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return repo.findByCustomerId(customerId).stream().map(this::toResponse).toList();
    }

    @Override
    public AccountResponse updateBalance(int id, AccountUpdateRequest req) {
        if (req.balance.compareTo(BigDecimal.ZERO) < 0) throw new com.batyrhan.bankapi.exception.InvalidInputException("Balance must be >= 0");
        repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        repo.updateBalance(id, req.balance);
        AccountBase updated = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        return toResponse(updated);
    }

    @Override
    public void delete(int id) {
        repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        repo.delete(id);
    }
}