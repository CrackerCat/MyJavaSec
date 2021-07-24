package com.test;

import sun.misc.BASE64Encoder;

import java.io.*;

public class CCDemo2Util {
    public static void main(String[] args) throws Exception{
        // HelloTemplatesImpl.class Path
        String path = "D:\\Code\\DES\\study\\target\\classes\\com\\test\\HelloTemplatesImpl.class";
        ByteArrayOutputStream out= new ByteArrayOutputStream();
        InputStream input = new FileInputStream(path);
        byte [] buff=new byte[1024];
        int len;
        while ((len=input.read(buff))!=-1){
            out.write(buff,0,len);
        }
        byte[] arr = out.toByteArray();
        final BASE64Encoder encoder = new BASE64Encoder();
        String res = encoder.encode(arr);
        System.out.println(res);
    }
}
