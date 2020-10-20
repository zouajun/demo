package cn.zouajun.bzshop.frontend.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient("common-redis")
public interface CommonRedisFeignClient {
    //-------------order
    @GetMapping("/redis/order/selectOrderId")
    Long selectOrderId();
}
