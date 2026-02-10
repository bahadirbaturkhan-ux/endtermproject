package com.batyrhan.bankapi.dto;

public class CustomerResponse {
    public Integer id;
    public String name;
    public String email;

    public CustomerResponse(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}