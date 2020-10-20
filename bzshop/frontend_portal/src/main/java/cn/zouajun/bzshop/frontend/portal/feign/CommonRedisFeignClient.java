package cn.zouajun.bzshop.frontend.portal.feign;

import cn.zouajun.bzshop.pojo.TbItem;
import cn.zouajun.bzshop.pojo.TbItemDesc;
import cn.zouajun.bzshop.pojo.TbItemParamItem;
import cn.zouajun.bzshop.utils.CatResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("common-redis")
public interface CommonRedisFeignClient {

    //-------------redis/ItemCategory
    @PostMapping("/redis/itemCategory/insertItemCategory")
    void insertItemCategory(@RequestBody CatResult catResult);

    @GetMapping("/redis/itemCategory/selectItemCategory")
    CatResult selectItemCategory();

    //--------------redis/Content
    @PostMapping("/redis/content/insertContentAD")
    void insertContentAD(@RequestBody List<Map> list);

    @GetMapping("/redis/content/selectContentAD")
    List<Map> selectContentAD();

    //--------------/redis/item
    @PostMapping("/redis/item/insertItemBaseInfo")
    void insertItemBaseInfo(@RequestBody TbItem tbItem);

    @GetMapping("/redis/item/selectItemBaseInfo")
    TbItem selectItemBaseInfo(@RequestParam Long tbItemId);

    @PostMapping("/redis/item/insertItemDesc")
    void insertItemDesc(@RequestBody TbItemDesc tbItemDesc);

    @GetMapping("/redis/item/selectItemDesc")
    TbItemDesc selectItemDesc(@RequestParam Long itemId);

    @PostMapping("/redis/item/insertItemParamItem")
    void insertItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);

    @GetMapping("/redis/item/selectItemParamItem")
    TbItemParamItem selectItemParamItem(@RequestParam Long itemId);

}
