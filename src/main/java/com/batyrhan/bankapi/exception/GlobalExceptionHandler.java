package com.batyrhan.bankapi.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> notFound(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err("NOT_FOUND", e.getMessage()));
    }

    @ExceptionHandler({InvalidInputException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<?> badRequest(Exception e) {
        String msg = (e instanceof MethodArgumentNotValidException manv)
                ? manv.getBindingResult().getAllErrors().get(0).getDefaultMessage()
                : e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err("BAD_REQUEST", msg));
    }

    @ExceptionHandler({DuplicateResourceException.class, DuplicateKeyException.class})
    public ResponseEntity<?> conflict(Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err("CONFLICT", e.getMessage()));
    }

    @ExceptionHandler({BadSqlGrammarException.class, DatabaseOperationException.class})
    public ResponseEntity<?> db(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err("DB_ERROR", e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> other(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err("ERROR", e.getMessage()));
    }

    private Map<String, Object> err(String code, String message) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("code", code);
        m.put("message", message);
        return m;
    }
}