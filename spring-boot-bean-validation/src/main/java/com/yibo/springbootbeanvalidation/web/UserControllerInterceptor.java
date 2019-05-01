package com.yibo.springbootbeanvalidation.web;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: huangyibo
 * @Date: 2018/12/9 19:01
 * @Description:
 */

public class UserControllerInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //把校验逻辑存放在这里
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        int status = response.getStatus();
        if(status == HttpStatus.BAD_REQUEST.value()){//HttpStatus.BAD_REQUEST.value()就是400
            response.setStatus(HttpStatus.OK.value());
        }
    }

}
