package com.batyrhan.bankapi.patterns.factory;

import com.batyrhan.bankapi.exception.InvalidInputException;
import com.batyrhan.bankapi.model.CheckingAccount;
import com.batyrhan.bankapi.model.AccountBase;
import com.batyrhan.bankapi.model.SavingsAccount;
import com.batyrhan.bankapi.patterns.singleton.AppConfigSingleton;

import java.math.BigDecimal;

public class AccountFactory {

    public AccountBase create(Integer id, String accountNumber, BigDecimal balance, Integer customerId, String type) {
        String t = AppConfigSingleton.getInstance().normalizeType(type);
        return switch (t) {
            case "SAVINGS" -> new SavingsAccount(id, accountNumber, balance, customerId);
            case "CHECKING" -> new CheckingAccount(id, accountNumber, balance, customerId);
            default -> throw new InvalidInputException("Unsupported account type: " + type);
        };
    }
}