package com.yyd.yyd.frame.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class RedisHelper {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void setValue(String key, String value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    public void setValue(String key, String value, Duration duration) {
        redisTemplate.opsForValue().set(key, value, duration);
    }
    public Boolean setNX(String key, String value, long seconds) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, seconds, TimeUnit.SECONDS);
    }

    public Boolean setNX(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public void delKey(String key) {
        redisTemplate.delete(key);
    }

    public Long getTTL(String key) {
        return redisTemplate.getExpire(key);
    }

    public void setTTL(String key, long seconds) {
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    public void incr(String key) {
        redisTemplate.opsForValue().increment(key);
    }
}
