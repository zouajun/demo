package cn.zouajun.bzshop.item.controller;

import cn.zouajun.bzshop.item.service.UserService;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;
    @RequestMapping("/checkUserInfo/{checkValue}/{checkFlag}")
    public Result checkUserInfo(@PathVariable String checkValue, @PathVariable Integer checkFlag){
        try{
            System.out.println(checkValue+":"+checkFlag);
            return service.checkUserInfo(checkValue,checkFlag);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }
}
