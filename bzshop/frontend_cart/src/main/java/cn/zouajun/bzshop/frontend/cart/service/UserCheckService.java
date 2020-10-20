package cn.zouajun.bzshop.frontend.cart.service;

import cn.zouajun.bzshop.pojo.TbUser;

public interface UserCheckService {

    TbUser checkUserToken(String token);
}
