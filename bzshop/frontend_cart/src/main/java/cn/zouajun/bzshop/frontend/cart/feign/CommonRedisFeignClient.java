package cn.zouajun.bzshop.frontend.cart.feign;

import cn.zouajun.bzshop.pojo.TbUser;
import cn.zouajun.bzshop.utils.CartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("common-redis")
public interface CommonRedisFeignClient {
    //----------------------cart
    @PostMapping("/redis/cart/insertCart")
    void insertCart(@RequestBody Map<String,Object> map);

    @GetMapping("/redis/cart/selectCartByUserId")
    Map<String, CartItem> selectCartByUserId(@RequestParam("userId") String userId);

    //--------------------sso
    @PostMapping("/sso/redis/checkUserToken")
    TbUser checkUserToken(@RequestParam("token") String token);
}
