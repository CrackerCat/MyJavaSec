package com.test.shell;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "<h1>许少 YYDS</h1>";
    }

    @RequestMapping("/deserialize")
    @ResponseBody
    public String deserialize(@RequestParam String code) throws Exception{
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
        JSON.parse(code);
        return "deserialize";
    }

}
