package com.yibo.user.web.controller;

import com.yibo.user.domain.User;
import com.yibo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author: huangyibo
 * @Date: 2018/12/15 23:18
 * @Description:
 *
 * 用户服务REST API
 */

@RestController
public class UserRestApiController {

    @Autowired
    private UserService userService;

    /**
     *
     * @param name 请求参数名“name”的数据
     * @return 如果保存成功，返回{@link User}，否则返回<code>null</code>
     */
    @PostMapping("/user/save")
    public User saveUser(@RequestParam String name){
        User user = new User();
        user.setName(name);
        if(userService.save(user)){
            return user;
        }
        return null;
    }

    /**
     * 获取所有的用户数据
     * @return 获取所有的用户数据
     */
    @GetMapping("/user/list")
    public Collection<User> list(){

        return userService.findAll();
    }
}
