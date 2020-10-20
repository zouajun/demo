package cn.zouajun.bzshop.common.redis.service.impl;

import cn.zouajun.bzshop.common.redis.service.ItemCategoryService;
import cn.zouajun.bzshop.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public void insertItemCategory(CatResult catResult) {
        redisTemplate.opsForValue().set("frontend:category:redis:key",catResult);
    }

    @Override
    public CatResult selectItemCategory() {
        return (CatResult) redisTemplate.opsForValue().get("frontend:category:redis:key");
    }


}
