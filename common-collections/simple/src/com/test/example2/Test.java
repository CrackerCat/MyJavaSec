package com.test.example2;

public class Test {
    public static void main(String[] args)throws Exception {
        Object firstObject = User.class;
        ReflectionObject[] reflectionChains = {
                new ReflectionObject("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"GetName", new Class[]{String.class}}),
                new ReflectionObject("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, new Object[]{"hacker"}}),
        };
        new ReflectionChains(firstObject, reflectionChains).execute();
    }
}
