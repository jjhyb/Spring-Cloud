package com.yibo.springcloudstreamkafkademo.stream.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author: huangyibo
 * @Date: 2018/12/30 0:07
 * @Description:
 */
public interface MessageSink {
    /**
     * 消息接收（输入）的管道名称 = "yibo"
     */
    String INPUT = "yibo";

    @Input("yibo")
    SubscribableChannel yibo();
}
