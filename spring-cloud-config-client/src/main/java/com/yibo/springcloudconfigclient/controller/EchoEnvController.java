package com.yibo.springcloudconfigclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: huangyibo
 * @Date: 2018/12/12 0:30
 * @Description:
 */

@RestController
public class EchoEnvController {

    private final Environment environment;

    @Autowired
    public EchoEnvController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/echo/env/{name}")
    public Map<String,String> getEnvironment(@PathVariable String name){
        Map<String,String> map = new HashMap<>();
        map.put(name,environment.getProperty(name));
        return map;
    }
}
