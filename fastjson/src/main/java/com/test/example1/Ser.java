package com.test.example1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Ser {
    public static void main(String[] args) throws Exception{
        Student student = new Student();
        student.setName("test");
        student.setAge(80);
        String jsonstring1 = JSON.toJSONString(student, SerializerFeature.WriteClassName);
        String jsonstring2 = JSON.toJSONString(student);
        System.out.println(jsonstring1);
        System.out.println(jsonstring2);
    }
}
