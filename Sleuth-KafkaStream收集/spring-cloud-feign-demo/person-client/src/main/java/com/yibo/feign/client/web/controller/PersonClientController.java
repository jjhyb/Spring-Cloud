package com.yibo.feign.client.web.controller;

import com.yibo.feign.domain.Person;
import com.yibo.feign.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 保存
     * @param person {@link Person}
     * @return 如果成功，<code>true</code>
     */
    @PostMapping("/person/save")
    public boolean save(@RequestBody Person person){

        logger.info("{} save() : Person : {}",getClass().getSimpleName(),person);

        return this.personServiceInterface.save(person);
    }

    /**
     * 查询所有服务
     * @return
     */
    @GetMapping("/person/find/all")
    public Collection<Person> findAll(){

        logger.info("{} # save()",getClass().getSimpleName());

        return this.personServiceInterface.findAll();
    }
}
