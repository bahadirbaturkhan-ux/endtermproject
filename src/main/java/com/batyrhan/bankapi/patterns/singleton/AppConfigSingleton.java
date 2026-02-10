package com.batyrhan.bankapi.patterns.singleton;

public final class AppConfigSingleton {
    private static final AppConfigSingleton INSTANCE = new AppConfigSingleton();
    private AppConfigSingleton() {}
    public static AppConfigSingleton getInstance() { return INSTANCE; }
    public String normalizeType(String type) { return type == null ? "" : type.trim().toUpperCase(); }
}