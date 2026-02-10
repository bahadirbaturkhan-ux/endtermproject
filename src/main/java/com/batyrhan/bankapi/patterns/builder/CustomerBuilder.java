package com.batyrhan.bankapi.patterns.builder;

import com.batyrhan.bankapi.model.Customer;

public class CustomerBuilder {
    private Integer id;
    private String name;
    private String email;

    public CustomerBuilder id(Integer v) { this.id = v; return this; }
    public CustomerBuilder name(String v) { this.name = v; return this; }
    public CustomerBuilder email(String v) { this.email = v; return this; }

    public Customer build() { return new Customer(id, name, email); }
}