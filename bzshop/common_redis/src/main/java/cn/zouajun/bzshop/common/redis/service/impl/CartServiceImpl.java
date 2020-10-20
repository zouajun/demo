package cn.zouajun.bzshop.common.redis.service.impl;

import cn.zouajun.bzshop.common.redis.service.CartService;
import cn.zouajun.bzshop.utils.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${frontend_cart_redis_key}")
    private String frontend_cart_redis_key;

    /*
    * 缓存购物车
    * */
    @Override
    public void insertCart(Map<String, Object> map) {
        String userId = (String) map.get("userId");
        Map<String, CartItem> cart = (Map<String, CartItem>) map.get("cart");
        this.redisTemplate.opsForHash().put(this.frontend_cart_redis_key,userId,cart);
    }

    /*
    * 根据用户Id查询购物车
    * */
    @Override
    public Map<String, CartItem> selectCartByUserId(String userId) {
        return (Map<String, CartItem>) redisTemplate.opsForHash().get(this.frontend_cart_redis_key,userId);
    }
}
