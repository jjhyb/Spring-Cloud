package com.yibo.springcloudconfigclient.demo;

import java.util.*;

/**
 * @author: huangyibo
 * @Date: 2018/12/10 0:41
 * @Description:
 */
public class ObserverDemo {

    public static void main(String[] args) {
        MyObservable observable = new MyObservable();
        //增加订阅者
        observable.addObserver(new Observer() {
            @Override
            public void update(Observable obs, Object value) {
                System.out.println(value);
            }
        });

        observable.setChanged();

        //发布者通知，订阅者是被动感知(推模式)
        observable.notifyObservers("Hello world");
        echoIterator();

    }

    /**
     * 迭代器模式，这是主动获取，pull模式
     */
    public static void echoIterator(){
        List<Integer> values = Arrays.asList(1,2,3,4,5);
        Iterator<Integer> iterator = values.iterator();
        while (iterator.hasNext()){//这是通过循环主动去获取
            Integer value = iterator.next();
            System.out.println(value);
        }
    }

    public static class MyObservable extends Observable{
        public void setChanged() {
            super.setChanged();
        }
    }
}
