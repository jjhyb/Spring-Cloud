package com.yibo.springcloudhystrixclientdemo;

import rx.Observer;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.Random;

/**
 * @author: huangyibo
 * @Date: 2018/12/17 23:27
 * @Description:
 *
 * Reactive X Demo
 *
 * 演示Hystrix服务熔断的原理
 */
public class RxJavaDemo {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        Single.just("Hello world!")//just 发布数据
//                .subscribeOn(Schedulers.io()) //io操作使用这个，且会在多线程中执行，有可能看不到效果
                .subscribeOn(Schedulers.immediate())//订阅的线程池 immediate = Thread.currentThread()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {         //正常结束流程
                        System.out.println("执行结束！");
                    }

                    @Override
                    public void onError(Throwable throwable) {//异常流程（结束）
                        System.out.println("熔断保护！");
                    }

                    @Override
                    public void onNext(String s) {          //数据消费 s = "Hello world!"
                        int value = random.nextInt(200);

                        //如果随机的时间大于100，就触发容错
                        if(value >100){
                            throw new RuntimeException("TimeOut!");
                        }

                        System.out.println("hello world() costs " + value + " ms");
                    }
                });
    }
}
