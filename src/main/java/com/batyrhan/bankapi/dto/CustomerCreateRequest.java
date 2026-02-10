package com.batyrhan.bankapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerCreateRequest {
    @NotBlank public String name;
    @NotBlank @Email public String email;
}