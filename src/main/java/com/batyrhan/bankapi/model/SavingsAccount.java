package com.batyrhan.bankapi.model;

import java.math.BigDecimal;

public class SavingsAccount extends AccountBase {
    public SavingsAccount(Integer id, String accountNumber, BigDecimal balance, Integer customerId) {
        super(id, accountNumber, balance, customerId);
    }

    @Override public String getAccountType() { return "SAVINGS"; }
    @Override public BigDecimal calculateMonthlyFee() { return BigDecimal.ZERO; }
}
