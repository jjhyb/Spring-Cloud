package com.yibo.feign.service.provider.service;


import com.yibo.feign.service.provider.dao.PersonRepository;
import com.yibo.feign.service.provider.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: huangyibo
 * @Date: 2019/1/4 23:45
 * @Description:
 *
 * {@link Person} 服务
 */

@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * 存储 {@link Person}
     * @param person
     */
    public void save(Person person){
        personRepository.save(person);
    }

    public Page<Person> findList(Pageable pageable){
        return personRepository.findAll(pageable);
    }
}
