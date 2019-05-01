package com.yibo.springcloudstreamkafkademo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: huangyibo
 * @Date: 2018/12/26 23:44
 * @Description:
 *
 * Kafka 消费者监听器
 */

@Component
public class KafkaConsumerListener {

    @KafkaListener(topics = "${kafka.topic}")
    public void onMessage(String message){

        System.out.println("kafka 消费者监听器，接收到消息：" + message);
    }

    @KafkaListener(topics = "yibo")
    public void onYiBoMessage(String message){

        System.out.println("kafka 消费者监听器，接收到消息：" + message);
    }
}
