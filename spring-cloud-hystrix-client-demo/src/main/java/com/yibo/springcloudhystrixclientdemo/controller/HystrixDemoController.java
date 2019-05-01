package com.yibo.springcloudhystrixclientdemo.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author: huangyibo
 * @Date: 2018/12/17 22:45
 * @Description:
 *
 * Hystrix demo Controller
 */

@RestController
public class HystrixDemoController {

    private final Random random = new Random();

    /**
     * 当{@link #helloWorld()}方法调用失败或超时时，
     * fallbackMethod 方法{@link #errorContent()}作为替代返回
     * @return
     */
    @GetMapping("/hello-world")
    @HystrixCommand(fallbackMethod = "errorContent",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "100")
            })
    public String helloWorld() throws InterruptedException {
        int value = random.nextInt(200);
        System.out.println("hello world() costs " + value + " ms");

        //如果随机的时间大于100，就触发容错
        Thread.sleep(value);
        return "hello world";
    }

    public String errorContent(){
        return "Fault";
    }


    /**
     * 当{@link #helloWorld2()}方法调用失败或超时时，
     * fallbackMethod 方法{@link #errorContent()}作为替代返回
     * @return
     */
    @GetMapping("/hello-world-2")
    public String helloWorld2(){
        return new HelloWorldCommand().execute();
    }

    /**
     * 编程方式
     */
    private class HelloWorldCommand extends com.netflix.hystrix.HystrixCommand<String> {

        protected HelloWorldCommand() {
            super(HystrixCommandGroupKey.Factory.asKey("HelloWorld"),100);
        }

        @Override
        protected String run() throws Exception {
            int value = random.nextInt(200);
            System.out.println("hello world() costs " + value + " ms");

            //如果随机的时间大于100，就触发容错
            Thread.sleep(value);
            return "hello world";
        }

        //容错执行
        @Override
        protected String getFallback() {
            return HystrixDemoController.this.errorContent();
        }
    }
}
