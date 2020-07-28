package com.keith.rent.core.utils.jedis.impl;

import com.google.common.collect.Lists;
import com.keith.rent.core.config.JedisClient;
import com.keith.rent.core.utils.jedis.IJedis;
import com.keith.rent.core.utils.jedis.ItimeOutCache;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.*;

import static redis.clients.util.SafeEncoder.encode;
import static redis.clients.util.SafeEncoder.encodeMany;

/**
 * description:
 * author:
 */
public class JedisServiceImpl implements ItimeOutCache, IJedis {

    /**
     *  单个调用
     */
    public JedisServiceImpl(){
    }
    /**
     * 多次调用，注意要自行returnResource
     */
    public JedisServiceImpl(Jedis jc){
        this.jc = jc;
    }

    private Jedis jc = null;

    /**
     * 把二进制转成java对象
     * @param data
     * @return
     */
    public static<T> T change2Obj(byte[] data, Class<T> clazz){
        if(data == null){
            return null;
        }

        if(String.class.equals(clazz)){
            return (T)encode(data);
        }
        try(ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(in)){
            return (T)ois.readObject();
        }catch(IOException e){
            throw new RuntimeException(e);
        }catch(ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * java对象转成二进制
     * @param obj
     * @return
     */
    public static byte[] change2Data(Object obj){
        if(obj == null){
            return null;
        }
        if(obj instanceof String){
            return encode(obj.toString());
        }

        try(ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os)){
            oos.writeObject(obj);
            return os.toByteArray();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<String> keys(String pattern){
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.keys(pattern);
        }finally {
            if(this.jc==null) JedisClient.returnResource(jc);
        }
    }

    @Override
    public Boolean exist(String key) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.exists(key);
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }
    @Override
    public <T> T get(String key, Class<T> clazz) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            if(StringUtils.isEmpty(jc.get(encode(key)))) {
                return null;
            }
            return change2Obj(jc.get(encode(key)),clazz);
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);

        }
    }

    @Override
    public void put(String key, Object obj) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            jc.set(encode(key), change2Data(obj));
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);

        }

    }

    @Override
    public <T> List<T> mGet(Class<T> clazz, String... key) {
        Jedis jc = this.jc;
        List<T> returnList = Lists.newArrayList();
        try {
            if(jc == null)jc = JedisClient.getClient();
            List<byte[]> list = jc.mget(encodeMany(key));
            if(StringUtils.isEmpty(list)){
                return returnList;
            }
            for(int i = 0 ; i < list.size(); i ++ ){
                returnList.add(change2Obj(list.get(i), clazz));
            }
            return returnList;
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);

        }

    }

    @Override
    public void remove(String... key) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            jc.del(key);
        } finally {
            if(this.jc==null)JedisClient.returnResource(jc);

        }
    }

    public void put(String key, Object obj,int timeoutDelay){
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            jc.setex(encode(key),timeoutDelay,change2Data(obj));
        } finally {
            if(this.jc==null)JedisClient.returnResource(jc);

        }
    }
    @Override
    public void expire(String key, int timeoutDelay) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            jc.expire(key, timeoutDelay);
        } finally {
            if(this.jc==null)JedisClient.returnResource(jc);

        }

    }

    @Override
    public Long incr(String key) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.incr(encode(key));
        } finally {
            if(this.jc==null)JedisClient.returnResource(jc);

        }
    }

    @Override
    public <T>T hget(String key, String field, Class<T> clazz) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return change2Obj(jc.hget(encode(key), encode(field)),clazz);
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);

        }
    }

    @Override
    public String get(String key) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            if(StringUtils.isEmpty(jc.get(encode(key)))){
                return null;
            }
            return encode(jc.get(encode(key)));
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }

    @Override
    public Long hset(String key, String field, Object obj) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.hset(encode(key), encode(field),change2Data(obj));
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);

        }
    }

    @Override
    public Long lpush(String key, Object obj) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.lpush(encode(key),change2Data(obj));
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);

        }
    }

    @Override
    public <T> T rpop(String key,Class<T> clazz) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return change2Obj(jc.rpop(encode(key)), clazz);
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);

        }
    }

    @Override
    public <T> T brpop(String key,Class<T> clazz,Integer brpopWaitTime) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            List<byte[]> list = jc.brpop(brpopWaitTime, encode(key));
            if(StringUtils.isEmpty(list) || list.isEmpty()) {
                return null;
            }
            return change2Obj(list.get(1), clazz);
        }  finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }

    @Override
    public Long llen(String key) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.llen(encode(key));
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);

        }
    }

    @Override
    public <T> Map<String, T> hgetAll(String key, Class<T> clazz) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            Map<byte[],byte[]> map =  jc.hgetAll(encode(key));
            Map<String,T> newMap = new HashMap<String,T>(map.size(),1f);
            Set<Map.Entry<byte[],byte[]>> entrySet = map.entrySet();
            Iterator<Map.Entry<byte[],byte[]>> iter = entrySet.iterator();
            while (iter.hasNext()){
                Map.Entry<byte[],byte[]> entry = iter.next();
                newMap.put(encode(entry.getKey()), (T)change2Obj(entry.getValue(),clazz));
            }
            return newMap;
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);

        }
    }
    @Override
    public Boolean hexists(String key, String field) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.hexists(key, field);
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }



    @Override
    public Long hlen(String key) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.hlen(key);
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }



    @Override
    public <T> List<T> hvals(String key, Class<T> clazz) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            List<byte[]> list = jc.hvals(encode(key));
            List<T> newList = new ArrayList<T>(list.size());
            for(int i = 0,length = list.size();i < length;i++){
                newList.add(change2Obj(list.get(i), clazz));
            }
            return newList;
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }



    @Override
    public Long hdel(String key, String... fields) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.hdel(key,fields);
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }



    @Override
    public Set<String> hkeys(String key) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.keys(key);
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }

    @Override
    public <T> Set<T> smembers(String key, Class<T> clazz) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            Set<byte[]> set = jc.smembers(encode(key));
            Set<T> newSet = new HashSet<T>(set.size(),1f);
            for(byte[] k : set){
                newSet.add(change2Obj(k, clazz));
            }
            return newSet;
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }

    @Override
    public <T>Set<T> sRandMember(String key, int size,Class<T> clazz){
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            List<byte[]> list = jc.srandmember(encode(key), size);
            if(StringUtils.isEmpty(list)){
                return null;
            }
            Set<byte[]> set = new HashSet<>(list);
            Set<T> newSet = new HashSet<T>(set.size(),1f);
            for(byte[] k : set){
                newSet.add(change2Obj(k, clazz));
            }
            return newSet;
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }

    @Override
    public Long sadd(String key, Object obj) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.sadd(encode(key),change2Data(obj));
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }

    @Override
    public Long scard(String key) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.scard(encode(key));
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }

    @Override
    public Boolean sismember(String key, Object obj) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.sismember(encode(key),change2Data(obj));
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }

    @Override
    public Object eval(String script, int keyCount, String... params) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.eval(script, keyCount, params);
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }

    @Override
    public Long srem(String key, Object... member) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            byte[][]members = new byte[member.length][];
            for(int i = 0;i < member.length;i++){
                members[i]=change2Data(member[i]);
            }
            return jc.srem(encode(key),members);
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }
    @Override
    protected void finalize() throws Throwable {
        if(this.jc != null){
            JedisClient.returnResource(this.jc);
        }
        super.finalize();
    }

    @Override
    public Map<String, Long> hsetAll(String key, Map<String, Object> filedMap) {
        if (null == filedMap) return null;
        Jedis jc = this.jc;
        Map<String, Long> resultMap = new HashMap<>();
        try {
            if(jc == null)jc = JedisClient.getClient();
            for (Map.Entry<String, Object> m : filedMap.entrySet()) {
                resultMap.put(m.getKey(), jc.hset(encode(key), encode(m.getKey()), change2Data(m.getValue())));
            }
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
            return resultMap;
        }
    }



    @Override
    public Boolean setbit(String key, long offset, boolean value){
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.setbit(key, offset, value);
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }

    @Override
    public Boolean getbit(String key, long offset){
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.getbit(key, offset);
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }

    @Override
    public Long bitcount(String key){
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.bitcount(key);
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.setrange(key,offset,value);
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        Jedis jc = this.jc;
        try {
            if(jc == null)jc = JedisClient.getClient();
            return jc.getrange(key,startOffset,endOffset);
        }finally {
            if(this.jc==null)JedisClient.returnResource(jc);
        }
    }
}
