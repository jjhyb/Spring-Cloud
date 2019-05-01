package com.yibo.springcloudstreamkafkademo.web.controller;

import com.yibo.springcloudstreamkafkademo.stream.producer.MessageProducerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: huangyibo
 * @Date: 2018/12/26 23:24
 * @Description:
 *
 * Kafka 生产者 Controller
 */

@RestController
public class KafkaProducerController {

    private final KafkaTemplate kafkaTemplate;

    private final MessageProducerBean messageProducerBean;

    private final String topic;

    @Autowired
    public KafkaProducerController(KafkaTemplate kafkaTemplate,
                                   MessageProducerBean messageProducerBean,
                                   @Value("${kafka.topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.messageProducerBean = messageProducerBean;
        this.topic = topic;
    }

    /**
     * 通过KafkaTemplate {@link KafkaTemplate}发送
     * @param message
     * @return
     */
    @PostMapping("/message/send")
    public String sendMessage(@RequestParam String message){
        kafkaTemplate.send(topic,message);
        return "success";
    }

    /**
     * 通过消息生产者 Bean {@link MessageProducerBean} 发送消息
     * @param message
     * @return
     */
    @GetMapping("/message/send")
    public String send(@RequestParam String message){
        messageProducerBean.send(message);
        return "success";
    }

    /**
     * 通过消息生产者 Bean {@link MessageProducerBean} 发送消息
     * @param message
     * @return
     */
    @GetMapping("/message/send/yibo")
    public String sendToYiBo(@RequestParam String message){
        messageProducerBean.sendToYiBo(message);
        return "success";
    }
}
