package com.yibo.springcloudzuuldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient//服务注册、服务发现，对外暴露服务
public class SpringCloudZuulDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringCloudZuulDemoApplication.class, args);
	}

}

