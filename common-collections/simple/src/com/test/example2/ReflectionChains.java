package com.test.example2;

import java.io.Serializable;

/*
 * 一个用来模拟提供恶意代码的类,
 * 主要的功能是将 ReflectionObject进行串联调用,与ReflectionObject一起构成漏洞代码的一部分
 * */
class ReflectionChains implements Serializable {

    private Object firstObject;
    private ReflectionObject[] reflectionObjects;

    public ReflectionChains(Object firstObject, ReflectionObject[] reflectionObjects) {
        this.firstObject = firstObject;
        this.reflectionObjects = reflectionObjects;
    }

    public Object execute() throws Exception {
        Object concurrentObject = firstObject;
        for (ReflectionObject reflectionObject : reflectionObjects) {
            concurrentObject = reflectionObject.transform(concurrentObject);
        }
        return concurrentObject;
    }
}
