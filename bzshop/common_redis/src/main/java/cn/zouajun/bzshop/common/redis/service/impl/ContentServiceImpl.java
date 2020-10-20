package cn.zouajun.bzshop.common.redis.service.impl;

import cn.zouajun.bzshop.common.redis.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public void insertContentAD(List<Map> list) {
        redisTemplate.opsForValue().set("frontend:ad:redis:key:89",list);
    }

    @Override
    public List<Map> selectContentAD() {
        return (List<Map>) redisTemplate.opsForValue().get("frontend:ad:redis:key:89");
    }
}
