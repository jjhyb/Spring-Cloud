package com.yibo.springbootjdbc.webflux;

import com.yibo.springbootjdbc.domain.User;
import com.yibo.springbootjdbc.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author: huangyibo
 * @Date: 2018/12/7 0:01
 * @Description:
 */

@Component
public class UserHandler {

    private final UserRepository userRepository;


    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest){
        //在Spring Web MVC  中使用 @RequestBody
        //在Spring Web Flux 中使用 ServerRequest

        System.out.printf("[Thread : %s] UserHandler start saving user",Thread.currentThread().getName());

        //Mono<User> 类似于Optinal<User>
        Mono<User> userMono = serverRequest.bodyToMono(User.class);

        //map()相当于转换工作
        Mono<Boolean> booleanMono = userMono.map(userRepository::save);
        return ServerResponse.ok().body(booleanMono,Boolean.class);
    }

}
