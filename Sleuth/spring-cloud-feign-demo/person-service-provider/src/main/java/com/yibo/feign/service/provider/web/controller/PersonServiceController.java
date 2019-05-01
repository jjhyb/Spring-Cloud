package com.yibo.feign.service.provider.web.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yibo.feign.domain.Person;
import com.yibo.feign.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: huangyibo
 * @Date: 2018/12/22 21:14
 * @Description:
 *
 * {@link PersonService} 服务提供者控制器，(可以实现{@link PersonService}接口，也可以不实现)
 */

@RestController
public class PersonServiceController {

    private Map<Long,Person> persons = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Random random = new Random();

    /**
     * 保存
     * @param person {@link Person}
     * @return 如果成功，<code>true</code>
     */
    @PostMapping("/person/save")
    public boolean save(@RequestBody Person person){

        logger.info("{} save() : Person : {}",getClass().getSimpleName(),person);

        return persons.put(person.getId(),person) == null;
    }

    /**
     * 查询所有服务
     * @return
     */
    @GetMapping("/person/find/all")
    @HystrixCommand(fallbackMethod = "fallbackForFindAll",
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "100")
            })
    public Collection<Person> findAll() throws InterruptedException {
        logger.info("{} # findAll()",getClass().getSimpleName());
        //如果随机时间大于100，触发熔断
        int value = random.nextInt(200);
        System.out.println("findAll() costs " + value +" ms");
        Thread.sleep(value);
        return persons.values();
    }

    /**
     * {@link #findAll()} 的fallback 方法
     * @return 返回空集合
     */
    public Collection<Person> fallbackForFindAll(){
        logger.info("{} # fallbackForFindAll()",getClass().getSimpleName());
        System.err.println("fallbackForFindAll() is invoked!");
        return Collections.emptyList();
    }
}
