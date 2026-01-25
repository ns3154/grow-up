package com.example.redis.config;

import com.google.common.collect.Sets;
import org.redisson.api.HostNatMapper;
import org.redisson.config.ReadMode;
import org.redisson.config.SubscriptionMode;
import org.redisson.config.*;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.connection.balancer.RoundRobinLoadBalancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东
 * @date 2020/03/19 15:45
 * @since 1.0
 **/
@Configuration
@EnableTransactionManagement
public class RedisConfig {

    /**
     * redistemplate
     * 开启事物 使用 redisTemplate.setEnableTransactionSupport(true)
     *
     * @param redisConnectionFactory redisConnectionFactory
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String, java.lang.String>
     * @throws
     * @author 杨帮东
     * @date 2020/3/19 18:22
     * @since 1.0
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        // 在切库时出现 : Selecting a new database not supported due to shared connection
        // 关闭共享连接
        redisConnectionFactory.setShareNativeConnection(false);
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.string());
//        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

    /**
     * 事物管理器
     * redis 要想实现注解事物,必须要借助jdbc的事物管理器实现
     *
     * @param dataSource
     * @return org.springframework.transaction.PlatformTransactionManager
     * @throws
     * @author 杨帮东
     * @date 2020/3/19 17:52
     * @since 1.0
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    /****** 注释说明: Redisson config 读源码使用,学习使用,生产不要这样配置 **************************/
    @Bean("redissonSingleClient")
    public RedissonClient redissonSingleClient() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        // 连接池
        singleServerConfig.setConnectionPoolSize(64);
        // 订阅连接
        singleServerConfig.setSubscriptionConnectionPoolSize(50);
        singleServerConfig.setAddress("10.1.11.110:6390");
        singleServerConfig.setDnsMonitoringInterval(5000L);
        // 订阅连接最小活跃数
        singleServerConfig.setSubscriptionConnectionMinimumIdleSize(1);
        // 连接池 最小活跃数
        singleServerConfig.setConnectionMinimumIdleSize(24);
        singleServerConfig.setDatabase(0);
        singleServerConfig.setSubscriptionsPerConnection(5);
        singleServerConfig.setPassword("6390");
        singleServerConfig.setUsername("");
        // 重试3次抛出异常
        singleServerConfig.setRetryAttempts(3);
        // 重试默认毫秒数
        singleServerConfig.setRetryInterval(1500);
        // redis服务响应时间超时 单位:毫秒
        singleServerConfig.setTimeout(3000);
        singleServerConfig.setClientName("");
        // 连接redis服务 超时时间
        singleServerConfig.setConnectTimeout(10000);
        // 当连接数大于最小连接数,则超过该时间 则删除一些连接
        singleServerConfig.setIdleConnectionTimeout(10000);
//		singleServerConfig.setSslEnableEndpointIdentification(false);
//		singleServerConfig.setSslProvider(SslProvider.JDK);
//		singleServerConfig.setSslTruststore(new URL());
//		singleServerConfig.setSslTruststorePassword("");
//		singleServerConfig.setSslKeystore(new URL());
//		singleServerConfig.setSslKeystorePassword("");
        // 类似于 心跳
        singleServerConfig.setPingConnectionInterval(30000);
        singleServerConfig.setKeepAlive(false);
        singleServerConfig.setTcpNoDelay(false);
        return Redisson.create(config);
    }

    /**
     * 集群模式客户端
     *
     * @param
     * @return org.redisson.api.RedissonClient
     * @author 杨帮东
     * @version 1.0
     * @date 2020/12/4 00:02
     */
    public RedissonClient redissonClusterClient() {
        Config config = new Config();
        ClusterServersConfig clusterServersConfig = config.useClusterServers();
        clusterServersConfig.addNodeAddress(new String[]{"", ""});
        // 集群扫描时间
        clusterServersConfig.setScanInterval(5000);
        // 集群槽校验
        clusterServersConfig.setCheckSlotsCoverage(true);
        // TODO 映射器  有时间查下作用
        clusterServersConfig.setNatMapper(new HostNatMapper());
        clusterServersConfig.setSlaveConnectionPoolSize(64);
        // 从节点 连接失败重试时间间隔
        clusterServersConfig.setFailedSlaveReconnectionInterval(3000);
        // 从节点 从第一次执行失败开始 超过180000毫秒则将该节点踢出
        clusterServersConfig.setFailedSlaveCheckInterval(180000);
        clusterServersConfig.setMasterConnectionPoolSize(64);
        // 从节点 负载均衡
        clusterServersConfig.setLoadBalancer(new RoundRobinLoadBalancer());
        clusterServersConfig.setSubscriptionConnectionPoolSize(50);
        clusterServersConfig.setSlaveConnectionMinimumIdleSize(24);
        clusterServersConfig.setMasterConnectionMinimumIdleSize(24);
        clusterServersConfig.setSubscriptionConnectionMinimumIdleSize(1);
        // 读类型, SLAVE  MASTER MASTER_SLAVE
        clusterServersConfig.setReadMode(ReadMode.SLAVE);
        // 订阅操作类型 从节点,主节点
        clusterServersConfig.setSubscriptionMode(SubscriptionMode.MASTER);
        clusterServersConfig.setDnsMonitoringInterval(5000L);
        clusterServersConfig.setSubscriptionsPerConnection(5);
        clusterServersConfig.setPassword("");
        clusterServersConfig.setUsername("");
        // 重试次数,超过抛异常
        clusterServersConfig.setRetryAttempts(3);
        // 重试 时间间隔
        clusterServersConfig.setRetryInterval(1500);
        // redis 服务响应超时
        clusterServersConfig.setTimeout(3000);
        clusterServersConfig.setClientName("");
        // redis 连接超时
        clusterServersConfig.setConnectTimeout(0);
        // 当连接数大于最小连接数,则超过该时间 则删除一些连接
        clusterServersConfig.setIdleConnectionTimeout(10000);
//		clusterServersConfig.setSslEnableEndpointIdentification(false);
//		clusterServersConfig.setSslProvider(SslProvider.JDK);
//		clusterServersConfig.setSslTruststore(new URL());
//		clusterServersConfig.setSslTruststorePassword("");
//		clusterServersConfig.setSslKeystore(new URL());
//		clusterServersConfig.setSslKeystorePassword("");
//		clusterServersConfig.setPingConnectionInterval(0);
        clusterServersConfig.setKeepAlive(false);
        clusterServersConfig.setTcpNoDelay(false);

        return Redisson.create(config);
    }

    /**
     * 主从模式客户端
     * 特点 一主多从
     *
     * @param
     * @return org.redisson.api.RedissonClient
     * @author 杨帮东
     * @version 1.0
     * @date 2020/12/4 00:02
     */
    public RedissonClient redissonMasterSlaveClient() {
        Config config = new Config();
        MasterSlaveServersConfig masterSlaveServersConfig = config.useMasterSlaveServers();
        // host:port
        masterSlaveServersConfig.setMasterAddress("");
        masterSlaveServersConfig.setSlaveAddresses(Sets.newHashSet());
        masterSlaveServersConfig.setDatabase(0);
        masterSlaveServersConfig.setSlaveConnectionPoolSize(0);
        masterSlaveServersConfig.setFailedSlaveReconnectionInterval(0);
        masterSlaveServersConfig.setFailedSlaveCheckInterval(0);
        masterSlaveServersConfig.setMasterConnectionPoolSize(0);
        masterSlaveServersConfig.setLoadBalancer(new RoundRobinLoadBalancer());
        masterSlaveServersConfig.setSubscriptionConnectionPoolSize(0);
        masterSlaveServersConfig.setSlaveConnectionMinimumIdleSize(0);
        masterSlaveServersConfig.setMasterConnectionMinimumIdleSize(0);
        masterSlaveServersConfig.setSubscriptionConnectionMinimumIdleSize(0);
        masterSlaveServersConfig.setReadMode(ReadMode.SLAVE);
        masterSlaveServersConfig.setSubscriptionMode(SubscriptionMode.SLAVE);
        masterSlaveServersConfig.setDnsMonitoringInterval(0L);
        masterSlaveServersConfig.setSubscriptionsPerConnection(0);
        masterSlaveServersConfig.setPassword("");
        masterSlaveServersConfig.setUsername("");
        masterSlaveServersConfig.setRetryAttempts(0);
        masterSlaveServersConfig.setRetryInterval(0);
        masterSlaveServersConfig.setTimeout(0);
        masterSlaveServersConfig.setClientName("");
        masterSlaveServersConfig.setConnectTimeout(0);
        masterSlaveServersConfig.setIdleConnectionTimeout(0);
//		masterSlaveServersConfig.setSslEnableEndpointIdentification(false);
//		masterSlaveServersConfig.setSslProvider(SslProvider.JDK);
//		masterSlaveServersConfig.setSslTruststore(new URL());
//		masterSlaveServersConfig.setSslTruststorePassword("");
//		masterSlaveServersConfig.setSslKeystore(new URL());
//		masterSlaveServersConfig.setSslKeystorePassword("");
        masterSlaveServersConfig.setPingConnectionInterval(0);
        masterSlaveServersConfig.setKeepAlive(false);
        masterSlaveServersConfig.setTcpNoDelay(false);

        return Redisson.create(config);
    }

    /**
     * 哨兵模式
     *
     * @return org.redisson.api.RedissonClient
     * @author 杨帮东
     * @version 1.0
     * @date 2020/12/4 00:02
     */
    public RedissonClient redissonSentinelClient() {
        Config config = new Config();
        SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
        sentinelServersConfig.setMasterName("");
        sentinelServersConfig.setDatabase(0);
        sentinelServersConfig.setScanInterval(0);
        sentinelServersConfig.setNatMapper(new HostNatMapper());
        // 启动时 是否检查哨兵列表
        sentinelServersConfig.setCheckSentinelsList(false);
        sentinelServersConfig.setSlaveConnectionPoolSize(0);
        sentinelServersConfig.setFailedSlaveReconnectionInterval(0);
        sentinelServersConfig.setFailedSlaveCheckInterval(0);
        sentinelServersConfig.setMasterConnectionPoolSize(0);
        sentinelServersConfig.setLoadBalancer(new RoundRobinLoadBalancer());
        sentinelServersConfig.setSubscriptionConnectionPoolSize(0);
        sentinelServersConfig.setSlaveConnectionMinimumIdleSize(0);
        sentinelServersConfig.setMasterConnectionMinimumIdleSize(0);
        sentinelServersConfig.setSubscriptionConnectionMinimumIdleSize(0);
        sentinelServersConfig.setReadMode(ReadMode.SLAVE);
        sentinelServersConfig.setSubscriptionMode(SubscriptionMode.SLAVE);
        sentinelServersConfig.setDnsMonitoringInterval(0L);
        sentinelServersConfig.setSubscriptionsPerConnection(0);
        sentinelServersConfig.setPassword("");
        sentinelServersConfig.setUsername("");
        sentinelServersConfig.setRetryAttempts(0);
        sentinelServersConfig.setRetryInterval(0);
        sentinelServersConfig.setTimeout(0);
        sentinelServersConfig.setClientName("");
        sentinelServersConfig.setConnectTimeout(0);
        sentinelServersConfig.setIdleConnectionTimeout(0);
//		sentinelServersConfig.setSslEnableEndpointIdentification(false);
//		sentinelServersConfig.setSslProvider(SslProvider.JDK);
//		sentinelServersConfig.setSslTruststore(new URL());
//		sentinelServersConfig.setSslTruststorePassword("");
//		sentinelServersConfig.setSslKeystore(new URL());
//		sentinelServersConfig.setSslKeystorePassword("");
//		sentinelServersConfig.setPingConnectionInterval(0);
//		sentinelServersConfig.setKeepAlive(false);
//		sentinelServersConfig.setTcpNoDelay(false);
        return Redisson.create(config);
    }


}
