package com.keith.rent.web.redis;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
public interface RedisConsumer {
    /**
     * 功能描述：消费方法，消费者类必须继承此方法
     * @param :
     * @return
     * @throws :
     * @author :向阳
     * @Date :2020/6/10
     */
    void deal(String message);
}
