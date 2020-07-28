package com.keith.rent.core.utils.jedis;

/**
 * description:
 * author:
 */
public interface ItimeOutCache {


    public void put(String key, Object obj, int timeoutDelay);

    public void expire(String key, int timeoutDelay);
}
