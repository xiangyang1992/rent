package com.keith.rent.core.utils.jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description:
 * author:
 */
public interface IJedis extends ItimeOutCache ,ICache{

    /**
     * 返回key对应哈希表的field字段值，类似Map.get
     * @param key
     * @param field
     * @param clazz
     * @return
     */
    <T>T hget(String key, String field, Class<T> clazz);
    /**
     * 获取指定的key的值
     * @param key
     * @return
     */
    String get(String key);
    /**
     * 设置key对应的哈希表field字段为obj，类似Map.put
     * @param key
     * @param field
     * @param obj
     * @return
     */
    Long hset(String key, String field, Object obj);
    /**
     *  存储REDIS队列 将值插入到列表头部
     * @param key
     * @param obj
     * @return
     */
    Long lpush(String key, Object obj);
    /**
     *  取REDIS队列 从列表尾部取值
     * @param key
     * @return
     */
    <T>T rpop(String key,Class<T> clazz);
    /**
     *  取REDIS队列 从列表尾部取值 只有在有元素时才返回，没有则会阻塞直到超时返回null
     * @param key
     * @param clazz
     * @param brpopWaitTime 堵塞等待时间（s）,规定时间没有取到值，返回null
     * @return
     */
    <T> T brpop(String key,Class<T> clazz,Integer brpopWaitTime);

    /**
     *  Llen 命令用于返回列表的长度  如果列表 key 不存在，则 key 被解释为一个空列表，返回 0 。 如果 key 不是列表类型，返回一个错误
     * @param key
     * @return
     */
    Long llen(String key);
    /**
     * 返回key对应的哈希表
     * @param key
     * @param clazz
     * @return
     */
    <T> Map<String, T> hgetAll(String key, Class<T> clazz);
    /**
     * 返回key对应哈希表的field字段是否有值，类似Map.containsKey
     * @param key
     * @param field
     * @return
     */
    Boolean hexists(String key, String field);

    /**
     * key对应哈希表的元素个数，类似Map.size
     * @param key
     * @param obj
     * @return
     */
    Long hlen(String key);

    /**
     * key对应哈希表的所有值，类似Map.values
     * @param key
     * @param obj
     * @return
     */
    <T> List<T> hvals(String key, Class<T> clazz);

    /**
     * key对应哈希表的所有键，类似Map.keys
     * @param key
     * @param obj
     * @return
     */
    Set<String> hkeys(String key);

    /**
     * 删除key对应哈希表的fields字段，类似removeKeys
     * @param key
     * @param obj
     * @return
     */
    Long hdel(String key, String... fields);


    /**
     * 返回key对应集合
     * @param key
     * @param clazz
     * @return
     */
    <T>Set<T> smembers(String key,  Class<T> clazz);

    /**
     * 从set集合中返回指定数量的列表
     * @param key
     * @param size
     * @param clazz
     * @param <T>
     * @return
     */
    <T>Set<T> sRandMember(String key, int size,Class<T> clazz);

    /**
     * key对应集合添加元素，类似Set.add
     * @param key
     * @param obj
     * @return
     */
    Long sadd(String key, Object obj);

    /**
     * key对应集合的元素个数，类似Set.size
     * @param key
     * @param obj
     * @return
     */
    Long scard(String key);
    /**
     * key对应集合是否存在obj，类似Set.contains
     * @param key
     * @param obj
     * @return
     */
    Boolean sismember(String key, Object obj);

    /**
     * 从key对应集合删除对象，类似Set.remove
     * @param key
     * @param member
     * @return
     */
    Long srem(String key,Object... member);


    /**
     *　根据表达式获取符合规则的key数据
     * @param pattern
     * @return
     */
    Set<String> keys(String pattern);

    /**
     * 将 key 中储存的数字值增一 ,返回当前key自增完的值
     * @param key
     * @return
     */
    Long incr(String key);


    /***
     * @Description 执行lua脚本
     * @Date 18:27 2019/2/25
     * @Param [script lua脚本, keyCount 参数个数, params 参数]
     * @Return java.lang.Object
     * @Throws
     */
    Object eval(String script, int keyCount, String... params);


    /**
     * 批量获取key的value值
     * @param clazz
     * @param key
     * @param <T>
     * @return
     */
    <T> List<T> mGet(Class<T> clazz, String... key);

    /**
     * 插入key对应的哈希表
     * @param key
     * @param filedMap
     * linwenhao
     * 20190214
     * @return
     */
    Map<String, Long> hsetAll(String key, Map<String, Object> filedMap);

    /**
     * 写一个位
     *
     * @param key 键
     * @param offset 偏移量，从0开始
     * @param value true表示1，false表示0
     */
    Boolean setbit(String key, long offset, boolean value);

    /**
     * 读一个位
     *
     * @param key 键
     * @param offset 偏移量，从0开始
     */
    Boolean getbit(String key, long offset);

    /**
     * 统计位中1的数量
     *
     * @param key 键
     */
    Long bitcount(String key);

    /**
     * 对指定key，设置value下标处的字符。
     */
    Long setrange(String key, long offset, String value);

    /**
     * 对指定key，获取value下标处的字符
     */
    String getrange(String key, long startOffset, long endOffset);


}
