package com.batyrhan.bankapi.repository;

import com.batyrhan.bankapi.model.AccountBase;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    int create(AccountBase a);
    Optional<AccountBase> findById(int id);
    List<AccountBase> findAll();
    List<AccountBase> findByCustomerId(int customerId);
    void updateBalance(int id, BigDecimal balance);
    void delete(int id);
}