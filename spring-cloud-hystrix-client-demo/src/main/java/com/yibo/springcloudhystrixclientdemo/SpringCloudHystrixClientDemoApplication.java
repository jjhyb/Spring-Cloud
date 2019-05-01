package com.yibo.springcloudhystrixclientdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
//@EnableHystrix
@EnableCircuitBreaker
@EnableTurbine
public class SpringCloudHystrixClientDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringCloudHystrixClientDemoApplication.class, args);
	}

}

