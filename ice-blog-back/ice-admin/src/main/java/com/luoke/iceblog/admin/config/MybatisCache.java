package com.luoke.iceblog.admin.config;

import com.luoke.iceblog.common.util.ApplicationContextUtil;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 *  自定义mybatis cache，使用redis作为mybatis 缓存
 * @author luoke
 * @date 2021/11/2 10:26
 */
public class MybatisCache implements Cache{
    private final String id;

    /**
     * RedisTemplate 对象，用于获取 HashOperation 对象
     */
    private RedisTemplate<Object,Object> redisTemplate;

    /**
     *  操作 Redis 数据库中 Hash 类型
     */
    private HashOperations<Object,Object,Object> hashOperations;


    public MybatisCache(String id) {
        this.id = id;
    }

    /**
     *  初始化 RedisTemplate 和 HashOperations 对象
     */
    public void initRedisTemplate() {
        if (redisTemplate == null) {
            this.redisTemplate = ApplicationContextUtil.getBean("redisTemplate", RedisTemplate.class);
            this.hashOperations = this.redisTemplate.opsForHash();
        }
    }


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        this.initRedisTemplate();
        this.hashOperations.put(this.id,key.toString(),value);
    }

    @Override
    public Object getObject(Object key) {
        this.initRedisTemplate();
        return this.hashOperations.get(this.id,key.toString());
    }

    @Override
    public Object removeObject(Object key) {
        this.initRedisTemplate();
        return this.hashOperations.delete(this.id,key.toString());
    }

    @Override
    public void clear() {
        this.initRedisTemplate();
        this.hashOperations.delete(this.id);
    }

    @Override
    public int getSize() {
        this.initRedisTemplate();
        return this.hashOperations.size(this.id).intValue();
    }
}
