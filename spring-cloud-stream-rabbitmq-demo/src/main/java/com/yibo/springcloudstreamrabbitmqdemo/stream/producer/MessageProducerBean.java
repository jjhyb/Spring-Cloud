package com.yibo.springcloudstreamrabbitmqdemo.stream.producer;

import com.yibo.springcloudstreamrabbitmqdemo.stream.messaging.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author: huangyibo
 * @Date: 2018/12/27 23:51
 * @Description:
 *
 * 消息生产者Bean
 */

@Component
@EnableBinding({Source.class,MessageSource.class})
public class MessageProducerBean {

    @Autowired
    @Qualifier(Source.OUTPUT)//Bean的名称
    private MessageChannel messageChannel;

    @Autowired
    private Source source;

    @Autowired
    @Qualifier(MessageSource.OUTPUT)
    private MessageChannel yiboMessageChannel;

    /**
     * 发送消息
     * @param message 消息内容
     */
    public void send(String message){
        //通过消息管道发送消息
        //messageChannel.send(MessageBuilder.withPayload(message).build());
        source.output().send(MessageBuilder.withPayload(message).build());
    }

    /**
     * 发送消息到yibo
     * @param message 消息内容
     */
    public void sendToYiBo(String message){
        yiboMessageChannel.send(MessageBuilder.withPayload(message).build());
    }

}
