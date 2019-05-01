package com.yibo.user.service;

import com.yibo.user.domain.User;

import java.util.Collection;

/**
 * @author: huangyibo
 * @Date: 2018/12/15 23:11
 * @Description:
 *
 * 用户服务
 */
public interface UserService {

    /**
     * 保存用户
     * @param user
     * @return 如果保存成功返回true,否则返回false
     */
    public boolean save(User user);

    /**
     * 查询所有的用户对象，
     * @return 不会返回null 没有返回空集合
     */
    public Collection<User> findAll();
}
