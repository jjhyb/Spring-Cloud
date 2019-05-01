package com.yibo.springcloudstreamrabbitmqdemo.web.controller;

import com.yibo.springcloudstreamrabbitmqdemo.stream.producer.MessageProducerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: huangyibo
 * @Date: 2018/12/26 23:24
 * @Description:
 *
 * RabbitMQ 生产者 Controller
 */

@RestController
public class RabbitMQProducerController {


    private final MessageProducerBean messageProducerBean;

    private final String topic;

    @Autowired
    public RabbitMQProducerController(MessageProducerBean messageProducerBean,
                                   @Value("${rabbitmq.topic}") String topic) {
        this.messageProducerBean = messageProducerBean;
        this.topic = topic;
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
