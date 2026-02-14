package com.batyrhan.bankapi.service.impl;

import com.batyrhan.bankapi.cache.InMemoryCache;
import com.batyrhan.bankapi.service.CacheService;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {

    private final InMemoryCache cache = InMemoryCache.getInstance();

    @Override
    public void clearAll() {
        cache.clear();
    }

    @Override
    public void invalidate(String key) {
        cache.invalidate(key);
    }
}