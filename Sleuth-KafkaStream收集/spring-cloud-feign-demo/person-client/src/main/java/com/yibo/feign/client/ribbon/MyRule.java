package com.yibo.feign.client.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * @author: huangyibo
 * @Date: 2018/12/23 2:53
 * @Description:
 *
 * 自定义实现 {@link IRule}
 */
public class MyRule extends AbstractLoadBalancerRule {

    //total = 0 当total >= 5的时候指针才能往下走
    //index = 0 当前对位提供服务的服务器地址
    //当total >= 5时，total需要重新置为0，因为total已经打到过5次，那么index也需要+1
    private int total = 0;      //总共被调用的次数，目前要求每台机器被调用5次
    private int currentIndex = 0;//当前提供服务的机器号

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers();
                List<Server> allList = lb.getAllServers();
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

                /*int index = this.chooseRandomInt(serverCount);
                server = (Server)upList.get(index);*/


               //自定义实现ribbon的负载均衡策略的关键代码
               //这是线程不安全的写法，线程安全要加锁
                if(total < 5){
                    server = upList.get(currentIndex);
                    total++;
                }else{
                    total = 0;
                    currentIndex++;
                    if(currentIndex >= upList.size()){
                        currentIndex = 0;
                    }
                }

                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}
