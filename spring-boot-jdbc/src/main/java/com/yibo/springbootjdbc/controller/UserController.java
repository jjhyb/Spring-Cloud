package com.yibo.springbootjdbc.controller;

import com.yibo.springbootjdbc.domain.User;
import com.yibo.springbootjdbc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author: huangyibo
 * @Date: 2018/12/6 23:52
 * @Description:
 *
 * 用户 RestController on WebMVC
 */

@RestController
public class UserController {

    private final UserRepository userRepository;

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/web/mvc/user/save")
    public boolean save(@RequestBody User user){
        System.out.printf("[Thread : %s] UserController start saving user",Thread.currentThread().getName());
        return  userRepository.save(user);
    }

    //这是在WebMvc的模式下实现异步的方式，用的是java8 Lambda方式写的线程池
    @PostMapping("/web/mvc/user/saveUser")
    public boolean saveUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        Future<Boolean> future = executorService.submit(() -> {
            return userRepository.save(user);
        });

        return future.get();
    }
}
