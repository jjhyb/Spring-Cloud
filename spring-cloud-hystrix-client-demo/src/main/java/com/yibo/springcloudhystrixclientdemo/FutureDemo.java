package com.yibo.springcloudhystrixclientdemo;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author: huangyibo
 * @Date: 2018/12/17 23:15
 * @Description:
 *
 * Future Demo
 */
public class FutureDemo {

    public static void main(String[] args) {
        Random random = new Random();

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<String> future = executorService.submit(() -> {//正常流程
            int value = random.nextInt(200);

            System.out.println("hello world() costs " + value + " ms");

            //如果随机的时间大于100，就触发容错
            Thread.sleep(value);
            return "Hello world";
        });

        try {
            future.get(100,TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            //超时流程
            System.out.println("超时保护！");
        }finally {
            executorService.shutdown();
        }
    }
}
