package com.keith.rent.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * description:
 * author:
 */

@Component
//@ConfigurationProperties(prefix = "spring.redis")
public class JedisClient {
    private static Logger logger = LoggerFactory.getLogger(JedisClient.class);

    public static String host = "42.194.205.32";

    public static int port = 6379;

    public static String password = "xiangyang1992@";

    public static int timeout = 5000;

    private static JedisPool jedisPool = null;



    public static void init(){
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(500);
            config.setMaxIdle(200);
            config.setMaxWaitMillis(5000);
            jedisPool = new JedisPool(config,host,port,timeout,password);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("刷新 redis pool失败");
        }
    }


    static {
        JedisClient.init();
    }


    /***
     * 获取Jedis实例
     *
     * @return
     */
    public  static Jedis getClient() {
        return jedisPool.getResource();
    }


    /**
     * 释放jedis资源
     *
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


}
