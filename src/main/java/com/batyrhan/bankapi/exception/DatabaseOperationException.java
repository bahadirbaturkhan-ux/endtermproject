package com.batyrhan.bankapi.exception;

public class DatabaseOperationException extends RuntimeException {
    public DatabaseOperationException(String message) { super(message); }
}