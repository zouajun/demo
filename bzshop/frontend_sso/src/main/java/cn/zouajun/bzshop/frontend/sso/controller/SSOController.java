package cn.zouajun.bzshop.frontend.sso.controller;

import cn.zouajun.bzshop.frontend.sso.service.SSOService;
import cn.zouajun.bzshop.pojo.TbUser;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/sso")
public class SSOController {

    @Autowired
    private  SSOService ssoService;
    /*
    * 对用户的注册信息做数据校验
    * */
    @RequestMapping("/checkUserInfo/{checkValue}/{checkFlag}")
    public Result checkUserInfo(@PathVariable String checkValue, @PathVariable Integer checkFlag){
        try{
            System.out.println(checkValue+":"+checkFlag);
            return ssoService.checkUserInfo(checkValue,checkFlag);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /*
    * 用户注册
    * */
    @RequestMapping("/userRegister")
    public Result userRegister(TbUser user){
        try{
            return ssoService.userRegister(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /*
    * 用户登录
    * */
    @RequestMapping("/userLogin")
    public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response){
        try{
            return ssoService.userLogin(username,password,request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /*
    * 用户退出登录
    * */
    @RequestMapping("/logOut")
    private Result logOut(String token){
        try{
            return ssoService.logOut(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }
}
