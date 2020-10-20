package cn.zouajun.bzshop.frontend.cart.service;

import cn.zouajun.bzshop.utils.Result;

public interface RedisCartService {
    Result addItem(Long itemId, String userId, Integer num);

    Result showCart(String userId);

    Result updateItemNum(Long itemId, Integer num, String userId);

    Result deleteItemFromCart(Long itemId, String userId);

    Result goSettlement(String[] ids, String userId);
}
