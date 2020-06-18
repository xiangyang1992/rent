package com.keith.rent.web.consumer;

import com.keith.rent.core.annotation.MqConsumer;
import com.keith.rent.web.redis.RedisConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */
@MqConsumer(topic = "topic1")
public class TopicRecevier implements RedisConsumer {
    private static final Logger log = LoggerFactory.getLogger(TopicRecevier.class);

    @Override
    public void deal(String message) {
        log.info("topic1收到消息：" + message);
    }
}
