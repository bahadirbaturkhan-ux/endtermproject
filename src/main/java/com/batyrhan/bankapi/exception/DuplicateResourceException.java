package com.batyrhan.bankapi.exception;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) { super(message); }
}