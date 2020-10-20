package cn.zouajun.bzshop.frontend.order.feign;

import cn.zouajun.bzshop.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("frontend-cart")
public interface FrontendCartFeignClient {

    @PostMapping("/cart/deleteItemFromCart")
    Result deleteItemFromCart(@RequestParam("itemId") Long itemId, @RequestParam("userId") String userId);
}
