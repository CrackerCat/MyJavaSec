package com.test.example1;

import java.io.*;

public class Main {

    public static byte[] serialize(final MyObject obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        objOut.writeObject(obj);
        return out.toByteArray();
    }

    public static Object deserialize(final byte[] serialized) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(serialized);
        ObjectInputStream objIn = new ObjectInputStream(in);
        return objIn.readObject();
    }

    public static void main(String[] args) throws Exception {
        byte[] serializeData = serialize(new MyObject());
        deserialize(serializeData);
    }
}
