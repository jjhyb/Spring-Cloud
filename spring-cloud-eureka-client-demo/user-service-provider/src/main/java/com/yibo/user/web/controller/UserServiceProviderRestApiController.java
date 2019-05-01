package com.yibo.user.web.controller;

import com.yibo.user.domain.User;
import com.yibo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author: huangyibo
 * @Date: 2018/12/15 23:38
 * @Description:
 *
 * {@link UserService 用户服务} 提供方REST API {@link Controller}
 */

@RestController
public class UserServiceProviderRestApiController {

    @Autowired
    private UserService userService;

    /**
     *
     * @param user 请求参数名“name”的数据
     * @return 如果保存成功，返回{@link User}，否则返回<code>null</code>
     */
    @PostMapping("/user/save")
    public User saveUser(@RequestBody User user){
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
