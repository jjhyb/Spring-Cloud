package com.yibo.feign.client;

import com.yibo.feign.service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: huangyibo
 * @Date: 2018/12/22 20:29
 * @Description:
 *
 * Person Client 应用程序入口
 */

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient//服务发现，对外暴露服务
@EnableFeignClients(clients = PersonService.class)
@EnableHystrix
//@RibbonClient(value = "person-service",configuration = MySelfRule.class)
public class PersonClientApplication {

    public static void main(String[] args) {

        SpringApplication.run(PersonClientApplication.class,args);
    }
}
