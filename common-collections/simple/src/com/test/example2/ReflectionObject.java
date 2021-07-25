package com.test.example2;

import java.io.Serializable;

/*
* 一个模拟拥有漏洞的类，主要提供的功能是根据自己的属性中的值来进行反射调用
* */
public class ReflectionObject implements Serializable {
    private String methodName;
    private Class[] paramTypes;
    private Object[] args;

    public ReflectionObject(String methodName, Class[] paramTypes, Object[] args) {
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.args = args;
    }

    //根据  methodName, paramTypes 来寻找对象的方法，利用 args作为参数进行调用
    public Object transform(Object input) throws Exception {
        Class inputClass = input.getClass();
        return inputClass.getMethod(methodName, paramTypes).invoke(input, args);
    }
}
