package com.yibo.feign.client.config;

import com.yibo.feign.client.ribbon.MyRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: huangyibo
 * @Date: 2018/12/23 17:06
 * @Description:
 */
@Configuration
public class MySelfRule {

    @Bean
    public MyRule myRule(){
        return new MyRule();
    }
}
