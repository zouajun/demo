package cn.zouajun.bzshop.frontend.sso.feign;

import cn.zouajun.bzshop.pojo.TbUser;
import cn.zouajun.bzshop.utils.CartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("common-redis")
public interface CommonRedisFeignClient {

    //-----------------sso
    @PostMapping("/sso/redis/insertUser")
    void insertUser(@RequestBody TbUser user, @RequestParam("token") String token);

    @PostMapping("/sso/redis/logOut")
    void logOut(@RequestParam("token") String token);

    //----------------------cart
    @PostMapping("/redis/cart/insertCart")
    void insertCart(@RequestBody Map<String,Object> map);

    @GetMapping("/redis/cart/selectCartByUserId")
    Map<String, CartItem> selectCartByUserId(@RequestParam("userId") String userId);
}
