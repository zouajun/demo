package cn.zouajun.bzshop.frontend.cart.service;

import cn.zouajun.bzshop.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookieCartService {

    Result addItem(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);

    Result showCart(HttpServletRequest request, HttpServletResponse response);

    Result updateItemNum(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);

    Result deleteItemFromCart(Long itemId, HttpServletRequest request, HttpServletResponse response);
}
