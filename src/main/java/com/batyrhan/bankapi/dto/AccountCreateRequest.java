package com.batyrhan.bankapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class AccountCreateRequest {
    @NotBlank public String accountNumber;
    @NotBlank public String type;
    @NotNull @DecimalMin("0.00") public BigDecimal balance;
    @NotNull public Integer customerId;
}