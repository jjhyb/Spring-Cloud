package com.yibo.springcloudconfigclientdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: huangyibo
 * @Date: 2018/12/15 1:12
 * @Description:
 *
 * 输出的配置项内容
 */

@RestController
@RefreshScope
//@RefreshScope 当关联的配置项或配置项属性更新的时候，关联的Bean的属性或字段也会相应的发生变化
public class EchoController {

    @Value("${my.name}")
    private String myName;

    @GetMapping("/my-name")
    public String getName(){
        return myName;
    }
}
