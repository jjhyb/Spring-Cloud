package com.yibo.springrest.config;

import com.yibo.springrest.http.message.PropertiesPersonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author: huangyibo
 * @Date: 2018/12/4 22:40
 * @Description:
 *
 * WebMvc 的配置
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        System.err.println("converters : " + converters);
//        converters.set(0,new MappingJackson2XmlHttpMessageConverter());
//        converters.add(new MappingJackson2HttpMessageConverter());

        converters.add(new PropertiesPersonHttpMessageConverter());
    }
}
