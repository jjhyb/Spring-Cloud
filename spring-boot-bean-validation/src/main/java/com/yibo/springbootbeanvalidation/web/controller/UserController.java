package com.yibo.springbootbeanvalidation.web.controller;

import com.yibo.springbootbeanvalidation.domain.User;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author: huangyibo
 * @Date: 2018/12/9 18:26
 * @Description:
 */

@RestController
public class UserController {

    @PostMapping("/user/save")
    public User save(@Valid @RequestBody User user){

        return user;
    }

    @PostMapping("/user/save2")
    public User save2(@RequestBody User user){
        //API调用的方式
        Assert.hasText(user.getName(),"名称不能为空");

        //JVM断言
        assert user.getId() <= 10000;
        return user;
    }
}
