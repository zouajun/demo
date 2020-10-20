package cn.zouajun.bzshop.common.redis.service;

import cn.zouajun.bzshop.utils.CartItem;

import java.util.Map;

public interface CartService {
    void insertCart(Map<String, Object> map);

    Map<String, CartItem> selectCartByUserId(String userId);
}
