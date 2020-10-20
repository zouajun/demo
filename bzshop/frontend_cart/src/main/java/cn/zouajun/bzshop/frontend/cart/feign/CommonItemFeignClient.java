package cn.zouajun.bzshop.frontend.cart.feign;

import cn.zouajun.bzshop.pojo.TbItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("common-item")
public interface CommonItemFeignClient {
    //-------------------Item
    @GetMapping("/service/item/findById")
    TbItem findById(@RequestParam Long itemId);
}
