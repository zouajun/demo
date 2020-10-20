package cn.zouajun.bzshop.common.redis.controller;

import cn.zouajun.bzshop.common.redis.service.SSOService;
import cn.zouajun.bzshop.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sso/redis")
public class SSOController {

    @Autowired
    private SSOService ssoService;

    /*
    * 将用户缓存到redis中
    * */
    @RequestMapping("/insertUser")
    public void insertUser(@RequestBody TbUser user, @RequestParam String token){
        ssoService.insertUser(user,token);
    }

    @RequestMapping("/logOut")
    public void logOut(@RequestParam String token){
        ssoService.logOut(token);
    }

    /*
    * 根据用户token校验用户在redis中是否失效
    * */
    @RequestMapping("/checkUserToken")
    public TbUser checkUserToken(@RequestParam String token){
        return ssoService.checkUserToken(token);
    }

}
