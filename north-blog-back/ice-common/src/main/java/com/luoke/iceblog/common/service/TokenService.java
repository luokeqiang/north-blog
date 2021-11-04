package com.luoke.iceblog.common.service;

import cn.hutool.core.util.IdUtil;
import com.luoke.iceblog.common.constant.TokenConstant;
import com.luoke.iceblog.common.redis.RedisCache;
import com.luoke.iceblog.common.vo.BaseUserVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 *  token 相关的service
 * @author luoke
 * @date 2021/11/3 9:48
 */
@Component
public class TokenService {
    @Value("${token.header}")
    private String tokenHeader;

    @Value("${token.expire}")
    private long tokenExpire;

    @Resource
    RedisCache redisCache;

    /**
     *  验证 token 有效性
     * @return true: 验证成功，false: 验证失败
     */
    public boolean verifyToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        return false;
    }

    /**
     *  使用 UUID 生成一个随机token
     * @return token
     */
    public String getToken(BaseUserVo baseUserVo) {
        String token = IdUtil.simpleUUID();
        refreshToken(token,baseUserVo);
        return token;
    }

    public <T> T getUser(String token) {
        return redisCache.getObjectValue(getRedisToken(token));
    }
    public  <T> T getUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        return getUser(token);
    }

    /**
     *  刷新token
     * @param token redis token key
     * @param baseUserVo 用户信息
     */
    private void refreshToken(String token,BaseUserVo baseUserVo){
        String redisTokenKey = getRedisToken(token);
        // 设置过期时间
        baseUserVo.setExpireTime(LocalDateTime.now().plusMinutes(tokenExpire));
        // 保存进redis
        redisCache.setObjectValue(redisTokenKey,baseUserVo,tokenExpire, TimeUnit.MINUTES);
    }

    /**
     *  获取redis token key
     * @param token token
     * @return redis token key
     */
    private String getRedisToken(String token){
        return TokenConstant.LOGIN_TOKEN_KEY+token;
    }

}
