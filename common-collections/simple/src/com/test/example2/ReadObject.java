package com.test.example2;

import java.io.IOException;
import java.io.Serializable;

/**
 * 一个等待序列化的类,拥有一个属性和一个重写了的readObject方法
 * 并且在readObject方法中执行了该属性的一个方法
 */
public class ReadObject implements Serializable {

    private ReflectionChains reflectionChains;

    public ReadObject(ReflectionChains reflectionChains) {
        this.reflectionChains = reflectionChains;
    }

    //当反序列化的时候，这个代码会被调用
    //该方法被调用的时候其属性都是空
    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        try {
            //用来模拟当readObject的时候，对自身的属性进行了一些额外的操作
            reflectionChains = (ReflectionChains) stream.readFields().get("reflectionChains", null);
            reflectionChains.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
