package com.linghd.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ling on 2017/1/5.
 */
@Component
public class JedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    public long increment(String key, long var) {
        return redisTemplate.opsForValue().increment(key, var);
    }

//    public void lpush(String key, String value){
//        redisTemplate.opsForList().leftPush(key,value);
//    }
//    public String rpop(String key){
//        return (String) redisTemplate.opsForList().rightPop(key);
//    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        return set(key, value, null);
    }

    /**
     * @param key
     * @param value
     * @param expireTime
     * @return boolean    返回类型
     * @throws
     * @Title: set
     * @Description: 写入缓存带有效期
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            if (expireTime != null) {
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void pub(String channel, Object msg) {
        redisTemplate.convertAndSend(channel, msg);
    }
}
