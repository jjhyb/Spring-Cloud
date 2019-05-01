package com.yibo.springcloudstreamkafkademo.stream.consumer;

import com.yibo.springcloudstreamkafkademo.stream.messaging.MessageSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: huangyibo
 * @Date: 2018/12/28 23:08
 * @Description:
 *
 * 消息消费 Bean
 */

@Component
@EnableBinding({Sink.class})
public class MessageConsumerBean {

    @Autowired
    @Qualifier(Sink.INPUT)
    private SubscribableChannel subscribableChannel;

    @Autowired
    private Sink sink;


    /**
     * 当字段注入完成后的回调
     */
    @PostConstruct
    public void init(){
        //实现异步回调
        subscribableChannel.subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println("subscribe：" + message.getPayload());
            }
        });
    }

    /**
     * 通过@ServiceActivator 实现订阅消息
     * @param message
     */
    @ServiceActivator(inputChannel = Sink.INPUT)
    public void onMessage(Object message){
        System.out.println("@ServiceActivator：" + message);
    }

    /**
     * 通过@StreamListener 实现订阅消息 这种方式常用，因为简单
     * @param message
     */
    @StreamListener(Sink.INPUT)
    public void onMessage(String message){
        System.out.println("@StreamListener：" + message);
    }

    /**
     * 通过@StreamListener 实现订阅消息 这种方式常用，因为简单
     * @param message
     */
    @StreamListener(MessageSink.INPUT)
    public void onMessageToYiBo(String message){

        System.out.println("@StreamListener, 自定义Sink(MessageSink)：" + message);
    }

}
