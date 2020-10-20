package cn.zouajun.bzshop.frontend.cart.controller;

import cn.zouajun.bzshop.frontend.cart.service.CookieCartService;
import cn.zouajun.bzshop.frontend.cart.service.RedisCartService;
import cn.zouajun.bzshop.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CookieCartService cookieCartService;

    @Autowired
    private RedisCartService redisCartService;
    /*
    * 添加购物车
    * */
    @RequestMapping("/addItem")
    public Result addItem(Long itemId, String userId, @RequestParam(defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response){
        try{
            //在用户未登录的状态下
            if(StringUtils.isBlank(userId)){
                return cookieCartService.addItem(itemId,num,request,response);
            }else{//在用户已登录的状态下
                return redisCartService.addItem(itemId,userId,num);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /*
    * 查看购物车
    * */
    @RequestMapping("/showCart")
    public Result showCart(String userId,HttpServletRequest request,HttpServletResponse response){
        try{
            if (StringUtils.isBlank(userId)){
                //未登录状态
                return cookieCartService.showCart(request,response);
            }else{
                //已登录状态
                return redisCartService.showCart(userId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /*
    * 更新购物车数量
    * */
    @RequestMapping("/updateItemNum")
    public Result updateItemNum(Long itemId,String userId,Integer num,HttpServletRequest request,HttpServletResponse response){
        try{
            if (StringUtils.isBlank(userId)){
                //未登录状态
                return cookieCartService.updateItemNum(itemId,num,request,response);
            }else{
                //已登录状态
                return redisCartService.updateItemNum(itemId,num,userId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /*
    * 删除购物车
    * */
    @RequestMapping("/deleteItemFromCart")
    public   Result deleteItemFromCart(@RequestParam Long itemId,@RequestParam String userId,HttpServletRequest request,HttpServletResponse response){
        try{
            if (StringUtils.isBlank(userId)){
                //未登录状态
                return cookieCartService.deleteItemFromCart(itemId,request,response);
            }else{
                //已登录状态
                return redisCartService.deleteItemFromCart(itemId,userId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    @RequestMapping("/goSettlement")
    public Result goSettlement(String[] ids,String userId){
        try{
            return redisCartService.goSettlement(ids,userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

}
