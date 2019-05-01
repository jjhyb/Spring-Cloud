package com.yibo.user.repository;

import com.yibo.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: huangyibo
 * @Date: 2018/12/15 23:45
 * @Description:
 *
 * {@link User 用户}仓储存模型
 */
@Repository
public class UserRepository {

    private ConcurrentMap<Long,User> userRepository = new ConcurrentHashMap<>();

    private static final AtomicLong idGenerator = new AtomicLong();

    public boolean save(User user) {
        long id = idGenerator.incrementAndGet();
        user.setId(id);
        //putIfAbsent 如果存在就不存，防止重复存储
        return userRepository.putIfAbsent(id,user) == null;
    }

    public Collection<User> findAll() {
        return userRepository.values();
    }
}
