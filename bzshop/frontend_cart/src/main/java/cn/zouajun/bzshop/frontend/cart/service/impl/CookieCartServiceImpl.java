package cn.zouajun.bzshop.frontend.cart.service.impl;

import cn.zouajun.bzshop.frontend.cart.feign.CommonItemFeignClient;
import cn.zouajun.bzshop.frontend.cart.service.CookieCartService;
import cn.zouajun.bzshop.pojo.TbItem;
import cn.zouajun.bzshop.utils.CartItem;
import cn.zouajun.bzshop.utils.CookieUtils;
import cn.zouajun.bzshop.utils.JsonUtils;
import cn.zouajun.bzshop.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class CookieCartServiceImpl implements CookieCartService {

    @Value("${cart_cookie_name}")
    private String cart_cookie_name;

    @Autowired
    CommonItemFeignClient commonItemFeignClient;

    @Override
    public Result addItem(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        //1.获取临时购物车
        Map<String, CartItem> cart = this.getCart(request);
        //2.查询商品
        TbItem item = this.selectItemById(itemId);
        //3.向购物车中添加商品
        this.addItemToCart(cart,item,num,itemId);
        //4.将购物车通过cookie写回客户端浏览器
        this.addClientCookie(request,response,cart);
        return Result.ok();
    }

    @Override
    public Result showCart(HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> list = new ArrayList<>();
        Map<String,CartItem> cart = this.getCart(request);
        Set<String> keys = cart.keySet();
        for (String key:keys){
            list.add(cart.get(key));
        }
        return Result.ok(list);
    }

    @Override
    public Result updateItemNum(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        Map<String,CartItem> cart = this.getCart(request);
        CartItem item = cart.get(itemId.toString());
        if (item!=null){
            item.setNum(num);
        }
        this.addClientCookie(request,response,cart);
        return Result.ok();
    }

    @Override
    public Result deleteItemFromCart(Long itemId, HttpServletRequest request, HttpServletResponse response) {
        Map<String,CartItem> cart = this.getCart(request);
        cart.remove(itemId.toString());
        this.addClientCookie(request,response,cart);
        return Result.ok();
    }

    private void addClientCookie(HttpServletRequest request, HttpServletResponse response, Map<String, CartItem> cart) {
        String cartJson = JsonUtils.objectToJson(cart);
        CookieUtils.setCookie(request,response,this.cart_cookie_name,cartJson,true);
    }

    private void addItemToCart(Map<String, CartItem> cart, TbItem item, Integer num, Long itemId) {
        //先从购物车中取商品
        CartItem cartItem = cart.get(itemId.toString());
        if (cartItem==null){//没有相同的商品
            CartItem cartItem1 = new CartItem();
            cartItem1.setId(item.getId());
            cartItem1.setImage(item.getImage());
            cartItem1.setNum(num);
            cartItem1.setPrice(item.getPrice());
            cartItem1.setSellPoint(item.getSellPoint());
            cartItem1.setTitle(item.getTitle());
            cart.put(item.getId().toString(),cartItem1);
        }else {//存在相同商品
            cartItem.setNum(cartItem.getNum()+num);
        }
    }

    /*
    * 根据商品ID查询商品
    * */
    private TbItem selectItemById(Long itemId) {
        return this.commonItemFeignClient.findById(itemId);
    }

    /*
    * 获取购物车
    * */
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
}
