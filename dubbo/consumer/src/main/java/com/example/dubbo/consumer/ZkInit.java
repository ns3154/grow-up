package com.example.dubbo.consumer;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/06/03 16:01
 **/
public class ZkInit {

    public static void main(String[] args) throws Exception {
        init();
    }
    public static void init() throws Exception {
        String zkAddress = "127.0.0.1:2181";
        CuratorFramework zkClient = CuratorFrameworkFactory.builder().
                connectString(zkAddress).
                retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        zkClient.start();

        if (zkClient.checkExists().forPath("/dubbo/config/dubbo/dubbo.properties") == null) {
            zkClient.create().creatingParentsIfNeeded().forPath("/dubbo/config/dubbo/dubbo.properties");
        }
        zkClient.setData().forPath("/dubbo/config/dubbo/dubbo.properties",
                ("dubbo.registry.address=zookeeper://"+ zkAddress +
                        "\n" +
                        "dubbo.metadata-report.address=zookeeper://" + zkAddress).getBytes());
    }
}
