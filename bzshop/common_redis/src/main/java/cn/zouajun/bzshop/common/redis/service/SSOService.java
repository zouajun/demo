package cn.zouajun.bzshop.common.redis.service;

import cn.zouajun.bzshop.pojo.TbUser;

public interface SSOService {
    void insertUser(TbUser user, String token);

    void logOut(String token);

    TbUser checkUserToken(String token);
}
