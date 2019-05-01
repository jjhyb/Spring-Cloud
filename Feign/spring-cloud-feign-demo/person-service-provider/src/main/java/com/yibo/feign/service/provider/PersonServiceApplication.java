package com.yibo.feign.service.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @author: huangyibo
 * @Date: 2018/12/22 21:12
 * @Description:
 *
 * {@PersonService} 服务提供者
 */

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient//服务注册、服务发现，对外暴露服务
@EnableHystrix
public class PersonServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(PersonServiceApplication.class,args);
    }
}
