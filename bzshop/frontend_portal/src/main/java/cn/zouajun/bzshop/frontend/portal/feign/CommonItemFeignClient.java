package cn.zouajun.bzshop.frontend.portal.feign;

import cn.zouajun.bzshop.pojo.TbItem;
import cn.zouajun.bzshop.pojo.TbItemDesc;
import cn.zouajun.bzshop.pojo.TbItemParamItem;
import cn.zouajun.bzshop.utils.CatResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("common-item")
public interface CommonItemFeignClient {

    //-------------------itemCategory
    @PostMapping("/service/itemCategory/selectItemCategoryAll")
    public CatResult selectItemCategoryAll();

    //-------------------Item
    @GetMapping("/service/item/findById")
    TbItem findById(@RequestParam Long itemId);

    //-------------------ItemDesc
    @GetMapping("/service/itemDesc/findTbItemDescById")
    TbItemDesc selectItemDescByItemId(@RequestParam Long itemId);

    //-----------------ItemParamItem
    @GetMapping("/service/itemParamItem/findTbItemParamItemByItemId")
    TbItemParamItem selectTbItemParamItemByItemId(@RequestParam Long itemId);
}
