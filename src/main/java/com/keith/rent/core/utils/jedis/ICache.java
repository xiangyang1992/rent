package com.keith.rent.core.utils.jedis;

/**
 * 缓存接口
 * @author 陈汉东
 *
 */
public interface ICache {

    /**
     * 存入缓存中
     * @param key
     * @param obj
     */
    void put(String key, Object obj);

    /**
     * 从缓存中删除
     * @param key
     */
    void remove(String... key);

    /**
     * 获取缓存对象
     * @param key
     * @return
     */
    <T>T get(String key, Class<T> clazz);

    /**
     * 是否存在
     * @param key
     * @return
     */
    Boolean exist(String key);

}
