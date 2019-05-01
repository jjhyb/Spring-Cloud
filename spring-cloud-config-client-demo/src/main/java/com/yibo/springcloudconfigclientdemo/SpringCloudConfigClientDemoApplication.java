package com.yibo.springcloudconfigclientdemo;

import com.yibo.springcloudconfigclientdemo.health.MyHealthIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@SpringBootApplication
@EnableScheduling

/**
 * Spring Boot 中使用SpringTask 要做几部
 * 一，添加@EnableScheduling注解到入口类声明上面
 * 二，创建类，添加@Component注解(如果在其他类中创建定时任务方法需要此步骤)
 * 三，创建方法，添加@Scheduled注解
 *
 * @Scheduled(fixedRate=2000)：上一次开始执行时间点后2秒再次执行；
 *
 * @Scheduled(fixedDelay=2000)：上一次执行完毕时间点后2秒再次执行；
 *
 * @Scheduled(initialDelay=1000, fixedDelay=2000)：第一次延迟1秒执行，然后在上一次执行完毕时间点后2秒再次执行；
 *
 * @Scheduled(cron="* * * * * ?")：按cron规则执行。
 */
public class SpringCloudConfigClientDemoApplication {

	private final ContextRefresher contextRefresher;

	private final Environment environment;

	@Autowired
	public SpringCloudConfigClientDemoApplication(ContextRefresher contextRefresher, Environment environment) {
		this.contextRefresher = contextRefresher;
		this.environment = environment;
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringCloudConfigClientDemoApplication.class, args);
	}


	@Scheduled(initialDelay = 5000,fixedDelay  = 5000)
	public void autoRefresh(){
		Set<String> updatePropertyNames = contextRefresher.refresh();

		updatePropertyNames.forEach(property -> System.err.printf("[Thread : %s] 当前配置已更新，" +
						"具体Key：%s ，Value: %s \n",
				Thread.currentThread().getName(),property,environment.getProperty(property)));
	}

	@Bean
	public MyHealthIndicator myHealthIndicator(){
		return new MyHealthIndicator();
	}

}

