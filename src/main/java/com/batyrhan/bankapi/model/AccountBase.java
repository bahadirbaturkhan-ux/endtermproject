package com.batyrhan.bankapi.model;

import java.math.BigDecimal;

public abstract class AccountBase {
    private final Integer id;
    private final String accountNumber;
    private final BigDecimal balance;
    private final Integer customerId;

    protected AccountBase(Integer id, String accountNumber, BigDecimal balance, Integer customerId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerId = customerId;
    }

    public abstract String getAccountType();
    public abstract BigDecimal calculateMonthlyFee();

    public Integer getId() { return id; }
    public String getAccountNumber() { return accountNumber; }
    public BigDecimal getBalance() { return balance; }
    public Integer getCustomerId() { return customerId; }
}
