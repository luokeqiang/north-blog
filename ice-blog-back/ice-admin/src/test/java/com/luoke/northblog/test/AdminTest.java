package com.luoke.iceblog.test;

import com.luoke.iceblog.admin.AdminApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author luoke
 * @date 2021/10/29 10:06
 */
@SpringBootTest(classes = AdminApplication.class)
@RunWith(SpringRunner.class)
public class AdminTest {
    @Resource
    RedisTemplate redisTemplate;
    @Test
    public void testRedis() {
        redisTemplate.opsForValue().set("age","最高年龄");
        Object age = redisTemplate.opsForValue().get("age");
        System.out.println("age="+age  );
    }
}
