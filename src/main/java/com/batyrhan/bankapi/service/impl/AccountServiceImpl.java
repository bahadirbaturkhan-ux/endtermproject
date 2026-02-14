package com.batyrhan.bankapi.service.impl;

import com.batyrhan.bankapi.cache.CacheKeys;
import com.batyrhan.bankapi.cache.InMemoryCache;
import com.batyrhan.bankapi.dto.AccountCreateRequest;
import com.batyrhan.bankapi.dto.AccountResponse;
import com.batyrhan.bankapi.dto.AccountUpdateRequest;
import com.batyrhan.bankapi.exception.ResourceNotFoundException;
import com.batyrhan.bankapi.model.AccountBase;
import com.batyrhan.bankapi.patterns.factory.AccountFactory;
import com.batyrhan.bankapi.repository.AccountRepository;
import com.batyrhan.bankapi.service.AccountService;
import com.batyrhan.bankapi.service.CacheService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountFactory accountFactory;

    private final InMemoryCache cache = InMemoryCache.getInstance();
    private final CacheService cacheService;

    public AccountServiceImpl(AccountRepository accountRepository,
                              AccountFactory accountFactory,
                              CacheService cacheService) {
        this.accountRepository = accountRepository;
        this.accountFactory = accountFactory;
        this.cacheService = cacheService;
    }

    @Override
    public AccountResponse create(AccountCreateRequest req) {

        // Factory expects: (id, accountNumber, balance, customerId, type)
        AccountBase account = accountFactory.create(
                null,
                req.accountNumber,
                req.balance,
                req.customerId,
                req.type
        );

        int newId = accountRepository.create(account);

        // invalidate cache after change
        cacheService.invalidate(CacheKeys.ACCOUNTS_ALL);

        // return response (no AccountResponse.from in your DTO)
        return new AccountResponse.Builder()
                .id(newId)
                .accountNumber(req.accountNumber)
                .balance(req.balance)
                .type(req.type)
                .customerId(req.customerId)
                .build();
    }

    @Override
    public AccountResponse getById(int id) {
        AccountBase a = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found: " + id));

        return toResponse(a);
    }

    @Override
    public List<AccountResponse> getAll() {

        @SuppressWarnings("unchecked")
        List<AccountResponse> cached =
                (List<AccountResponse>) cache.get(CacheKeys.ACCOUNTS_ALL);

        if (cached != null) return cached;

        List<AccountResponse> fresh = accountRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();

        cache.put(CacheKeys.ACCOUNTS_ALL, fresh);
        return fresh;
    }

    @Override
    public List<AccountResponse> getByCustomerId(int customerId) {
        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public AccountResponse updateBalance(int id, AccountUpdateRequest req) {

        // make sure account exists
        AccountBase existing = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found: " + id));

        accountRepository.updateBalance(id, req.balance);

        // invalidate cache after change
        cacheService.invalidate(CacheKeys.ACCOUNTS_ALL);

        // return updated (re-read)
        AccountBase updated = accountRepository.findById(id)
                .orElse(existing);

        return toResponse(updated);
    }

    @Override
    public void delete(int id) {

        // make sure account exists
        accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found: " + id));

        accountRepository.delete(id);

        // invalidate cache after change
        cacheService.invalidate(CacheKeys.ACCOUNTS_ALL);
    }

    private AccountResponse toResponse(AccountBase a) {
        // AccountBase subclasses likely have getters; if not, use fields accordingly
        return new AccountResponse.Builder()
                .id(a.getId())
                .accountNumber(a.getAccountNumber())
                .balance(a.getBalance())
                .type(a.getClass().getSimpleName())
                .customerId(a.getCustomerId())
                .build();
    }
}