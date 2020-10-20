package cn.zouajun.bzshop.common.redis.service.impl;

import cn.zouajun.bzshop.common.redis.service.SSOService;
import cn.zouajun.bzshop.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SSOServiceImpl implements SSOService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${user_session_redis_key}")
    private String key;

    @Override
    public void insertUser(TbUser user, String token) {
        user.setPassword("");
        this.redisTemplate.opsForValue().set(key+":"+token,user,1, TimeUnit.DAYS);
    }

    @Override
    public void logOut(String token) {
        this.redisTemplate.delete(key+":"+token);
    }

    @Override
    public TbUser checkUserToken(String token) {
        return (TbUser) this.redisTemplate.opsForValue().get(key+":"+token);
    }
}
