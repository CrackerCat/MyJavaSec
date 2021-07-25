package com.test.example3;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10001);
        while (true) {
            Socket socket = server.accept();
            execute(socket);
        }
    }
    public static void execute(final Socket socket){
        new Thread(new Runnable() {
            public void run() {
                try {
                    ObjectInputStream is  = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                    Object obj = is.readObject();
                    System.out.println(obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
