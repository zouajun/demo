package cn.zouajun.bzshop.frontend.cart.service.impl;

import cn.zouajun.bzshop.frontend.cart.feign.CommonItemFeignClient;
import cn.zouajun.bzshop.frontend.cart.feign.CommonRedisFeignClient;
import cn.zouajun.bzshop.frontend.cart.service.RedisCartService;
import cn.zouajun.bzshop.pojo.TbItem;
import cn.zouajun.bzshop.utils.CartItem;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RedisCartServiceImpl implements RedisCartService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    @Override
    public Result addItem(Long itemId, String userId, Integer num) {
        //1.查询商品
        TbItem tbItem = this.selectItemById(itemId);
        //2.获取购物车
        Map<String, CartItem> cart = this.getCart(userId);
        //3.将商品加入购物车
        this.addItemToCart(tbItem,cart,num,itemId);
        //4.向redis写入购物车
        this.addCartToRedis(userId,cart);
        return Result.ok();
    }

    @Override
    public Result showCart(String userId) {
        List<CartItem> list = new ArrayList<>();
        Map<String,CartItem> cart = this.getCart(userId);
        Set<String> keys = cart.keySet();
        for (String key:keys) {
            list.add(cart.get(key));
        }
        return Result.ok(list);
    }

    @Override
    public Result updateItemNum(Long itemId, Integer num, String userId) {
        //1.获取购物车
        Map<String,CartItem> cart = this.getCart(userId);
        CartItem i = cart.get(itemId.toString());
        if (i!=null){
            i.setNum(num);
        }
        this.addCartToRedis(userId,cart);
        return Result.ok();
    }

    @Override
    public Result deleteItemFromCart(Long itemId, String userId) {
        Map<String,CartItem> cart = getCart(userId);
        cart.remove(itemId.toString());
        this.addCartToRedis(userId,cart);
        return Result.ok();
    }

    @Override
    public Result goSettlement(String[] ids, String userId) {
        //1.获取购物车
        Map<String,CartItem> cart = this.getCart(userId);
        //2.从购物车中获取选中的商品
        List<CartItem> list = this.getItemList(cart,ids);
        return Result.ok(list);
    }

    private List<CartItem> getItemList(Map<String, CartItem> cart, String[] ids) {
        List<CartItem> list = new ArrayList<>();
        for (String id:ids) {
            list.add(cart.get(id));
        }
        return list;
    }

    private void addCartToRedis(String userId, Map<String, CartItem> cart) {
        Map<String ,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("cart",cart);
        commonRedisFeignClient.insertCart(map);
    }

    private void addItemToCart(TbItem item, Map<String, CartItem> cart, Integer num, Long itemId) {
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

    private Map<String, CartItem> getCart(String userId) {
        try{
            Map<String,CartItem> cart = this.commonRedisFeignClient.selectCartByUserId(userId);
            if (cart == null){
                cart = new HashMap<>();
            }
            return cart;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    private TbItem selectItemById(Long itemId) {
        return commonItemFeignClient.findById(itemId);
    }


}
