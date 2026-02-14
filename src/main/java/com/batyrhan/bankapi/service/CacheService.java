package com.batyrhan.bankapi.service;

/**
 * Service abstraction for cache operations.
 * Keeps controllers unaware of cache implementation details.
 */
public interface CacheService {
    void clearAll();
    void invalidate(String key);
}