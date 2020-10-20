package cn.zouajun.bzshop.frontend.sso.service.impl;

import cn.zouajun.bzshop.frontend.sso.feign.CommonRedisFeignClient;
import cn.zouajun.bzshop.frontend.sso.service.SSOService;
import cn.zouajun.bzshop.mapper.TbUserMapper;
import cn.zouajun.bzshop.pojo.TbUser;
import cn.zouajun.bzshop.pojo.TbUserExample;
import cn.zouajun.bzshop.utils.*;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class SSOServiceImpl implements SSOService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    @Value("${cart_cookie_name}")
    private String cart_cookie_name;

    @Override
    public Result checkUserInfo(String checkValue, Integer checkFlag) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if (checkFlag==1){//校验用户名
            criteria.andUsernameEqualTo(checkValue);
        }else if (checkFlag==2){//校验电话号码
            criteria.andPhoneEqualTo(checkValue);
        }
        Integer rows = this.tbUserMapper.countByExample(example);
        if (rows>0){
            return Result.error("数据不可用");
        }
        return Result.ok(checkValue);
    }

    @Override
    @LcnTransaction
    public Result userRegister(TbUser user) {
        //加密密码
        String pwd = MD5Utils.digest(user.getPassword());
        user.setPassword(pwd);
        //数据补齐
        user.setCreated(new Date());
        user.setUpdated(new Date());
        this.tbUserMapper.insert(user);
        return Result.ok();
    }

    @Override
    public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        //1.根据用户名密码查询数据库
        TbUser tbUser = this.login(username,password);
        if (tbUser==null){
            return Result.error("用户名密码错误");
        }
        //将用户添加到redis中
        String userToken = UUID.randomUUID().toString();
        Integer i = this.insertToRedis(tbUser,userToken);
        if (i==500){
            return Result.error("登录失败");
        }
        Map<String,String> map = new HashMap<>();
        map.put("token",userToken);
        map.put("userid",tbUser.getId().toString());
        map.put("username",tbUser.getUsername());
        //将临时购物车中的商品同步到redis中
        this.syncCart(tbUser.getId().toString(),request);
        //删除cookie
        this.deleteCookieCart(request,response);
        return Result.ok(map);
    }

    /*
    * 删除临时购物车
    * */
    private void deleteCookieCart(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request,response,this.cart_cookie_name);
    }

    private void syncCart(String userId, HttpServletRequest request) {
        //获取临时购物车
        Map<String ,CartItem> cookieCart = this.getCart(request);
        //获取永久购物车
        Map<String,CartItem> redisCart = this.getCart(userId);
        //删除永久购物车中所包含临时购物车中的商品
        Set<String> keys = cookieCart.keySet();
        for(String key:keys){
            redisCart.remove(key);
        }
        //将同步后的购物车缓存到redis中
        redisCart.putAll(cookieCart);
        //将永久购物车重新缓存到redis中
        this.addCartToRedis(userId,redisCart);
    }

    private void addCartToRedis(String userId, Map<String, CartItem> cart) {
        Map<String ,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("cart",cart);
        commonRedisFeignClient.insertCart(map);
    }

    @Override
    public Result logOut(String token) {
        this.commonRedisFeignClient.logOut(token);
        return Result.ok();
    }

    /*
    * 将登录信息添加到redis
    * */
    private Integer insertToRedis(TbUser tbUser, String userToken) {
        try {
            commonRedisFeignClient.insertUser(tbUser,userToken);
            return 200;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 500;
    }

    private TbUser login(String username, String password) {
        String pwd = MD5Utils.digest(password);
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria =  example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(pwd);
        List<TbUser> list = tbUserMapper.selectByExample(example);
        if (list==null||list.size()<=0){
            return null;
        }
        return list.get(0);
    }

    private Map<String, CartItem> getCart(HttpServletRequest request) {
        String cartJson = CookieUtils.getCookieValue(request,cart_cookie_name,true);
        if (StringUtils.isBlank(cartJson)){//不存在临时购物车
            return new HashMap<String, CartItem>();
        }
        //存在的临时购物车,需要做json转换
        try{
            Map<String,CartItem> map = JsonUtils.jsonToMap(cartJson,CartItem.class);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new HashMap<String, CartItem>();
    }

    private Map<String, CartItem> getCart(String userId) {
        try {
            Map<String, CartItem> cart = this.commonRedisFeignClient.selectCartByUserId(userId);
            if (cart == null) {
                cart = new HashMap<>();
            }
            return cart;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

}