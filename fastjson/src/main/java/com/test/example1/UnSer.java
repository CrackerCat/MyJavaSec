package com.test.example1;

import com.alibaba.fastjson.JSON;

public class UnSer {
    public static void main(String[] args) {
        String jsonStr="{\"@type\":\"com.test.example1.Student\",\"age\":80,\"name\":\"test\"}";
        System.out.println(JSON.parse(jsonStr).getClass());
        System.out.println(JSON.parseObject(jsonStr,Student.class).getClass());
    }
}
