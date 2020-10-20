package cn.zouajun.bzshop.item.service.impl;

import cn.zouajun.bzshop.item.service.UserService;
import cn.zouajun.bzshop.mapper.TbUserMapper;
import cn.zouajun.bzshop.pojo.TbUserExample;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TbUserMapper mapper;

    @Override
    public Result checkUserInfo(String checkValue, Integer checkFlag) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if (checkFlag==1){//校验用户名
            criteria.andUsernameEqualTo(checkValue);
        }else if (checkFlag==2){//校验电话号码
            criteria.andPhoneEqualTo(checkValue);
        }
        Integer rows = this.mapper.countByExample(example);
        if (rows>0){
            return Result.error("数据不可用");
        }
        return Result.ok(checkValue);
    }
}
