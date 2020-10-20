package cn.zouajun.bzshop.frontend.cart.service.impl;

import cn.zouajun.bzshop.frontend.cart.feign.CommonRedisFeignClient;
import cn.zouajun.bzshop.frontend.cart.service.UserCheckService;
import cn.zouajun.bzshop.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserCheckServiceImpl implements UserCheckService {

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    public TbUser checkUserToken(String token){
        return this.commonRedisFeignClient.checkUserToken(token);
    }
}
