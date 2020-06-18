package com.keith.rent.web.ctl;

import com.keith.rent.web.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */

@RestController
@Slf4j
public class MqRedisCtl {
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/topic1")
    public void addOne(String message) {
        redisUtil.addToMq("topic1", message);
    }
}
