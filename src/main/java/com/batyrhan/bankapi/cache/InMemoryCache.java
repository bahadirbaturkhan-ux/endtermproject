package com.batyrhan.bankapi.cache;

import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryCache {

    private static volatile InMemoryCache instance;
    private final ConcurrentHashMap<String, Object> store = new ConcurrentHashMap<>();

    private InMemoryCache() {}

    public static InMemoryCache getInstance() {
        if (instance == null) {
            synchronized (InMemoryCache.class) {
                if (instance == null) {
                    instance = new InMemoryCache();
                }
            }
        }
        return instance;
    }

    public Object get(String key) {
        return store.get(key);
    }

    public void put(String key, Object value) {
        store.put(key, value);
    }

    public void invalidate(String key) {
        store.remove(key);
    }

    public void clear() {
        store.clear();
    }
}