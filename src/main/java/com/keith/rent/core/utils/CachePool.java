package com.keith.rent.core.utils;

import com.keith.rent.core.utils.jedis.IJedis;
import com.keith.rent.core.utils.jedis.impl.JedisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * description:
 * author:
 */
public class CachePool {

    private static Logger logger = LoggerFactory.getLogger(CachePool.class);

    private static volatile IJedis jedis;

    private static void initJedisCache() {
        if (jedis == null) {
            jedis = new JedisServiceImpl();
        }
    }



    public static void put(String key, Object value, int second) {
//        defaultCache.put(key, value, second);
        initJedisCache();
        if(StringUtils.isEmpty(key) || ObjectUtils.isEmpty(value)){
            return;
        }
        jedis.hset(key, key, value);
        jedis.expire(key, second);
    }


    public static Object get(String key) {
        initJedisCache();
        if(StringUtils.isEmpty(key)){
            return null;
        }
        return jedis.hget(key, key, Object.class);

    }


    public static boolean hasCache(String key) {
        initJedisCache();
        if(StringUtils.isEmpty(key)){
            return false;
        }
        return jedis.hexists(key, key);

    }
}
