package com.batyrhan.bankapi.utils;

import java.lang.reflect.Method;

public class ReflectionUtils {
    public static void inspect(Object o) {
        Class<?> c = o.getClass();
        System.out.println(c.getName());
        for (Method m : c.getDeclaredMethods()) {
            System.out.println(m.getName());
        }
    }
}