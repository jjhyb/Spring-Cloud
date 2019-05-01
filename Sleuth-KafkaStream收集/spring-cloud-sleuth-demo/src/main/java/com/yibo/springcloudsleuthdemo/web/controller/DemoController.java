package com.yibo.springcloudsleuthdemo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: huangyibo
 * @Date: 2018/12/30 18:59
 * @Description:
 */

@RestController
public class DemoController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String index(){
        String returnValue = "Hello world";
        logger.info("{} index() : {}",getClass().getSimpleName(),returnValue);
        return returnValue;
    }

    /**
     * 完整调用链路
     * spring-cloud-sleuth
     *  --> spring-cloud-zuul
     *      --> person-client
     *          --> person-service
     * @return
     */
    @GetMapping("/to/zuul/person-client/person/find/all")
    public Object toZuul(){
        logger.info("spring-cloud-sleuth#toZuul()");
        String url = "http://spring-cloud-zuul/person-client/person/find/all";
        return restTemplate.getForObject(url,Object.class);
    }
}
