package com.yibo.springrest.controller;

import com.yibo.springrest.domain.Person;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author: huangyibo
 * @Date: 2018/12/3 23:17
 * @Description:
 */

@RestController
public class PersonRestController {

    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable Long id, @RequestParam(required = false) String name){
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        return person;
    }

    @PostMapping(value = "/person/json/to/properties",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = "application/properties+person")
    public Person personJson2Properties(@RequestBody Person person){
        return person;
    }

    @PostMapping(value = "/person/properties/to/json",
                consumes = "application/properties+person",         //请求类型，对应浏览器的Content-Type
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)   //响应类型，对应浏览器的Accept
    public Person personProperties2Json(@RequestBody Person person){
        return person;
    }
}
