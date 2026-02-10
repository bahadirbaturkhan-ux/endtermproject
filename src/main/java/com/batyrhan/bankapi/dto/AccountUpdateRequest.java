package com.batyrhan.bankapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class AccountUpdateRequest {
    @NotNull @DecimalMin("0.00") public BigDecimal balance;
}