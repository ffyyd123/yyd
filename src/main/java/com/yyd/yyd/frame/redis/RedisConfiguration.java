package com.yyd.yyd.frame.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;


import java.util.Arrays;
import java.util.List;


//@Configuration
public class RedisConfiguration {

//    @Value("${spring.redis.cluster.nodes}")
    private String nodes;
//    @Value("${spring.redis.password}")
    private String password;
//    @Value("${spring.redis.cluster.max-redirects:10}")
    private Integer maxRedirects;
//    @Value("${spring.redis.jedis.pool.max-wait:5000}")
    private Integer maxWait;
//    @Value("${spring.redis.jedis.pool.max-idle:200}")
    private Integer maxIdle;
//    @Value("${spring.redis.jedis.pool.min-idle:50}")
    private Integer minIdle;

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisConnectionFactory connectionFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setTestOnBorrow(true);

        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(clusterNodes());
        clusterConfiguration.setPassword(password);
        clusterConfiguration.setMaxRedirects(maxRedirects);
        return new JedisConnectionFactory(clusterConfiguration);
    }

    private List<String> clusterNodes() {
        return Arrays.asList(nodes.split(","));
    }
}
