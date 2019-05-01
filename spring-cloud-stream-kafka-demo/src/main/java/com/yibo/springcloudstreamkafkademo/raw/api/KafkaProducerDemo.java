package com.yibo.springcloudstreamkafkademo.raw.api;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author: huangyibo
 * @Date: 2018/12/26 1:28
 * @Description:
 *
 * Kafka Producer Demo(使用原始api)
 */
public class KafkaProducerDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //初始化配置
        Properties properties = new Properties();
        //bootstrap.servers=localhost:9092"配置在E:\software\kafka_2.11-2.1.0\config\producer.properties里面
        properties.setProperty("bootstrap.servers","localhost:9092");
        properties.setProperty("key.serializer",StringSerializer.class.getName());
        properties.setProperty("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        //创建 KafkaProducer
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer(properties);

        //创建Kafka消息 = ProducerRecord
        String topic = "test";
        Integer partition = 0;
        Long timestamp = System.currentTimeMillis();
        String key = "message-key";
        String value = "yibo";
        ProducerRecord<String,String> record = new ProducerRecord<>(topic,partition,timestamp,key,value);

        //发送Kafka消息
        Future<RecordMetadata> future = kafkaProducer.send(record);

        //因为Future是异步的，这里强制执行
        future.get();
    }
}
