package com.batyrhan.bankapi.patterns.builder;

import com.batyrhan.bankapi.model.AccountBase;
import com.batyrhan.bankapi.patterns.factory.AccountFactory;

import java.math.BigDecimal;

public class AccountBuilder {
    private Integer id;
    private String accountNumber;
    private BigDecimal balance;
    private Integer customerId;
    private String type;

    public AccountBuilder id(Integer v) { this.id = v; return this; }
    public AccountBuilder accountNumber(String v) { this.accountNumber = v; return this; }
    public AccountBuilder balance(BigDecimal v) { this.balance = v; return this; }
    public AccountBuilder customerId(Integer v) { this.customerId = v; return this; }
    public AccountBuilder type(String v) { this.type = v; return this; }

    public AccountBase build() {
        return new AccountFactory().create(id, accountNumber, balance, customerId, type);
    }
}