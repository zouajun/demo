package cn.zouajun.bzshop.frontend.portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient("common-content")
public interface CommonContentFeignClient {

    //-----------------Content
    @GetMapping("/service/content/selectFrontendContentByAD")
    List<Map> selectFrontendContentByAD();



}
