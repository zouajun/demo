package cn.zouajun.bzshop.item.service;

import cn.zouajun.bzshop.utils.Result;

public interface UserService {
    Result checkUserInfo(String checkValue, Integer checkFlag);
}
