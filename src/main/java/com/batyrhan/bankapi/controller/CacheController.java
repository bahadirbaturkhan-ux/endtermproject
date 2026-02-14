package com.batyrhan.bankapi.controller;

import com.batyrhan.bankapi.service.CacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Map<String, String>> clearAll() {
        cacheService.clearAll();
        return ResponseEntity.ok(Map.of("message", "Cache cleared"));
    }
}