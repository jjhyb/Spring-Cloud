package com.yibo.springcloudconfigclient.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: huangyibo
 * @Date: 2018/12/10 1:07
 * @Description:
 *
 * Spring 自定义 事件/监听器
 */
public class SpringEventListenerDemo {

    public static void main(String[] args) {

        //Annotatio驱动的Spring的上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        //注册监听器
        context.addApplicationListener(new ApplicationListener<MyApplicationEvent>() {
            /**
             * 监听器得到事件
             * @param event
             */
            @Override
            public void onApplicationEvent(MyApplicationEvent event) {
                System.out.println("接收到事件："+event.getSource() +"@"+event.getApplicationContext());
            }
        });

        context.refresh();

        //发布事件
        context.publishEvent(new MyApplicationEvent(context,"Hello world"));
        context.publishEvent(new MyApplicationEvent(context,1));
        context.publishEvent(new MyApplicationEvent(context,100));

    }

    public static class MyApplicationEvent extends ApplicationEvent{

        private final ApplicationContext applicationContext;

        public MyApplicationEvent(ApplicationContext applicationContext,Object source) {
            super(source);
            this.applicationContext = applicationContext;
        }

        public ApplicationContext getApplicationContext() {
            return applicationContext;
        }
    }
}
