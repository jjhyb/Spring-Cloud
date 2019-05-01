package com.yibo.feign.service;

import com.yibo.feign.domain.Person;
import com.yibo.feign.hystrix.PersonServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

/**
 * @author: huangyibo
 * @Date: 2018/12/22 20:01
 * @Description:
 *
 * {@link Person} 服务
 */

//@Component
@FeignClient(value = "person-service",fallback = PersonServiceFallback.class)//服务提供方应用名称
public interface PersonService {

    /**
     * 保存
     * @param person {@link Person}
     * @return 如果成功，<code>true</code>
     */
    @PostMapping("/person/save")
   boolean save(@RequestBody Person person);

    /**
     * 查询所有服务
     * @return
     */
    @GetMapping("/person/find/all")
    Collection<Person> findAll();
}
