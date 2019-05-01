package com.yibo.user;

import com.yibo.user.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: huangyibo
 * @Date: 2018/12/15 23:59
 * @Description:
 *
 * {@link UserService 用户服务} 引导类
 */

@SpringBootApplication
@EnableDiscoveryClient  //服务发现，对外暴露服务
@EnableEurekaClient     //本服务启动后会自动注册进Eureka服务中
public class UserServiceProviderBootstrap {

    public static void main(String[] args) {

        SpringApplication.run(UserServiceProviderBootstrap.class,args);
    }

}
