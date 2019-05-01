package com.yibo.user.service;

import com.yibo.user.domain.User;
import com.yibo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author: huangyibo
 * @Date: 2018/12/15 23:41
 * @Description:
 *
 * {@link UserService 用户服务}提供者实现
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Collection<User> findAll() {

        return userRepository.findAll();
    }
}
