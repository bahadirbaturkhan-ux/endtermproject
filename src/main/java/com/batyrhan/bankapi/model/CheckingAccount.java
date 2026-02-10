package com.batyrhan.bankapi.model;

import java.math.BigDecimal;

public class CheckingAccount extends AccountBase {
    public CheckingAccount(Integer id, String accountNumber, BigDecimal balance, Integer customerId) {
        super(id, accountNumber, balance, customerId);
    }

    @Override public String getAccountType() { return "CHECKING"; }
    @Override public BigDecimal calculateMonthlyFee() { return new BigDecimal("10.00"); }
}
