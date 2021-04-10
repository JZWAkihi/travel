package com.jiang.travels.cache;

import com.jiang.travels.utils.ApplicationContextUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/***
 * 自定义Redis缓存的实现
 */
public class RedisCache implements Cache {

    private final String id;

    public RedisCache(String id) {
        System.out.println("id = " + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }


    //代码冗余
    public RedisTemplate getRedisTemplate(){

        //通过application工具类获取redisTemplate
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        return redisTemplate;
    }


    //放入
    @Override
    public void putObject(Object key, Object value) {
        System.out.println("key = " + key);
        System.out.println("value = " + value);

        //将redishash类型作为缓存存储模型  key  hashkey  value
        getRedisTemplate().opsForHash().put(id.toString(),key.toString(),value);

    }

    //读取
    @Override
    public Object getObject(Object key) {
        System.out.println("key = " + key);
        return getRedisTemplate().opsForHash().get(id.toString(), key.toString());

    }

    @Override
    public Object removeObject(Object key) {
        System.out.println("removeObject ================  key = " + key);
        return null;
    }

    @Override
    public void clear() {
        System.out.println("clear ================  ");
        getRedisTemplate().delete(id.toString());

    }

    @Override
    public int getSize() {
        //获取hash中key value的数量
        return getRedisTemplate().opsForHash().size(id.toString()).intValue();
    }
}
