package com.yibo.springcloudstreamkafkademo.stream.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author: huangyibo
 * @Date: 2018/12/28 22:32
 * @Description:
 */
public interface MessageSource {

    /**
     * 消息来源的管道名称 = "yibo"
     */
    String OUTPUT = "yibo";

    @Output("yibo")
    MessageChannel yibo();

}
