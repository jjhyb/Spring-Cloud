package com.yibo.springcloudconfigclientdemo.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

/**
 * @author: huangyibo
 * @Date: 2018/12/15 17:21
 * @Description:
 */
public class MyHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.down().withDetail("MyHealthIndicator","down");
    }
}
