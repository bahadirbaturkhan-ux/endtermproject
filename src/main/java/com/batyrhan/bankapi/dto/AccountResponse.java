package com.batyrhan.bankapi.dto;

import java.math.BigDecimal;

public class AccountResponse {
    public Integer id;
    public String accountNumber;
    public BigDecimal balance;
    public String type;
    public Integer customerId;

    private AccountResponse() {}

    public static class Builder {
        private final AccountResponse r = new AccountResponse();
        public Builder id(Integer v) { r.id = v; return this; }
        public Builder accountNumber(String v) { r.accountNumber = v; return this; }
        public Builder balance(BigDecimal v) { r.balance = v; return this; }
        public Builder type(String v) { r.type = v; return this; }
        public Builder customerId(Integer v) { r.customerId = v; return this; }
        public AccountResponse build() { return r; }
    }
}