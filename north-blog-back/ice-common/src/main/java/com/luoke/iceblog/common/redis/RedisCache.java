package com.luoke.iceblog.common.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis Cache 封装类
 *
 * @author luoke
 * @date 2021/11/2 23:24
 */
@Component
public class RedisCache {
    @Resource
    private RedisTemplate redisTemplate;

    /**
     *  缓存基本 对象
     * @param key 缓存对象键值
     * @param value 缓存对象的值
     */
    public void setObjectValue(Object key,Object value) {
        this.redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 缓存基本对象，并设置有效时间
     * @param key 缓存对象的key
     * @param value 缓存对象的值
     * @param timeout 有效时间
     * @param timeUnit 时间单位 {@link TimeUnit}
     */
    public void setObjectValue(Object key, Object value, long timeout,TimeUnit timeUnit) {
        this.redisTemplate.opsForValue().set(key, value,timeout,timeUnit);
    }

    /**
     *  设置key的有效时间
     * @param key 缓存对象的key
     * @param timeout 有效时间
     * @param timeUnit 时间单位
     */
    public void expire(final Object key,final long timeout, final TimeUnit timeUnit){
        this.redisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     *  设置key的有效时间，单位为秒 {@link TimeUnit}
     * @param key 缓存对象的key
     * @param timeout 有效时间
     */
    public void expire(final Object key,final long timeout){
        this.expire(key, timeout,TimeUnit.SECONDS);
    }

    public <T> T getObjectValue(Object key) {
        ValueOperations<Object, T> opsForValue = this.redisTemplate.opsForValue();
        return opsForValue.get(key);
    }

    /**
     *  删除单个对象
     * @param key 缓存对象的key
     * @return true：删除成功，false：删除失败
     */
    public boolean delete(final Object key) {
        return this.redisTemplate.delete(key);
    }

    /**
     *  删除多个对象
     * @param keys 要删除对象的key 集合 {@link Collection}
     * @return 返回成功删除的对象个数
     */
    public <T> Long delete(final Collection<T> keys) {
        return this.redisTemplate.delete(keys);
    }

    /**
     *  删除多个对象
     * @param keys 要删除对象的key
     * @return 返回成功删除的对象个数
     */
    public <T> Long delete(final T... keys) {
        return delete(Arrays.asList(keys));
    }

    /**
     *  设置缓存map
     * @param key 缓存的key
     * @param data 缓存数据 map对象 {@link Map}
     * @param <T> map中 value 的类型
     */
    public <T> void setCacheMap(final String key,final Map<String,T> data){
        this.redisTemplate.opsForHash().putAll(key,data);
    }

    /**
     *  获取缓存中map对象
     * @param key 缓存对象的key
     * @param <T> map对象中value的类型
     * @return 返回Map对象 {@link Map}
     */
    public <T> Map<String,T> getCacheMap(final String key){
        return this.redisTemplate.opsForHash().entries(key);
    }

    /**
     *  设置map中某个key 的值
     * @param key redis key
     * @param hKey Hash key
     * @param value 要设置的值
     * @param <T> value的类型
     */
    public <T> void setCacheMapValue(final String key,final String hKey,final T value) {
        this.redisTemplate.opsForHash().put(key,hKey,value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getCacheMapValue(final String key, final String hKey)
    {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys)
    {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern)
    {
        return redisTemplate.keys(pattern);
    }

}
