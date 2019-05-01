package com.yibo.feign.client.web.controller;

import com.yibo.feign.domain.Person;
import com.yibo.feign.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author: huangyibo
 * @Date: 2018/12/22 20:32
 * @Description:
 */


@RestController
public class PersonClientController{

    @Autowired
    private PersonService personServiceInterface;

    /**
     * 保存
     * @param person {@link Person}
     * @return 如果成功，<code>true</code>
     */
    @PostMapping("/person/save")
    public boolean save(@RequestBody Person person){

        return this.personServiceInterface.save(person);
    }

    /**
     * 查询所有服务
     * @return
     */
    @GetMapping("/person/find/all")
    public Collection<Person> findAll(){

        return this.personServiceInterface.findAll();
    }
}
