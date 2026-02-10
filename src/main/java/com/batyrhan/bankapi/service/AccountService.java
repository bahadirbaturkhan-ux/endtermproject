package com.batyrhan.bankapi.service;

import com.batyrhan.bankapi.dto.AccountCreateRequest;
import com.batyrhan.bankapi.dto.AccountResponse;
import com.batyrhan.bankapi.dto.AccountUpdateRequest;

import java.util.List;

public interface AccountService {
    AccountResponse create(AccountCreateRequest req);
    AccountResponse getById(int id);
    List<AccountResponse> getAll();
    List<AccountResponse> getByCustomerId(int customerId);
    AccountResponse updateBalance(int id, AccountUpdateRequest req);
    void delete(int id);
}