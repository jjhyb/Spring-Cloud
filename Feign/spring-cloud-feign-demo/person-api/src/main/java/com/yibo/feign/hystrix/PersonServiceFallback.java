package com.yibo.feign.hystrix;

import com.yibo.feign.domain.Person;
import com.yibo.feign.service.PersonService;

import java.util.Collection;
import java.util.Collections;

/**
 * @author: huangyibo
 * @Date: 2018/12/23 18:13
 * @Description:
 */
public class PersonServiceFallback implements PersonService {

    @Override
    public boolean save(Person person) {
        return false;
    }

    @Override
    public Collection<Person> findAll() {
        return Collections.emptyList();
    }
}
