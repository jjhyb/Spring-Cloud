package com.yibo.user;

import com.yibo.user.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author: huangyibo
 * @Date: 2018/12/16 0:34
 * @Description:
 *
 * {@link UserService 用户服务} 消费引导类
 */

@SpringBootApplication
@EnableDiscoveryClient  //服务发现，对外暴露服务
@EnableEurekaClient     //本服务启动后会自动注册进Eureka服务中
public class UserServiceConsumerBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceConsumerBootstrap.class,args);
    }

    @LoadBalanced //用于加入 Ribbon 配置
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
