package com.batyrhan.bankapi.utils;

import com.batyrhan.bankapi.model.AccountBase;

import java.util.Comparator;
import java.util.List;

public class SortingUtils {
    public static List<AccountBase> byBalanceAsc(List<AccountBase> list) {
        return list.stream().sorted(Comparator.comparing(AccountBase::getBalance)).toList();
    }
}