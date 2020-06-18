package com.keith.rent.web.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * 功能描述:
     *
     * @param topic 队列名
     * @param obj  数据载体
     * @author xiangyang
     * @date 4/13/20 1:40 AM
     */
    public void addToMq(String topic, Object obj) {
        String str = obj instanceof String ? (String) obj : JSON.toJSONString(obj);
        redisTemplate.opsForList().leftPush(topic, str);
    }
}
