package cn.zouajun.bzshop.backend.item.feign;

import cn.zouajun.bzshop.backend.item.fallback.CommonItemFeignClientFallbackFactory;
import cn.zouajun.bzshop.pojo.*;
import cn.zouajun.bzshop.utils.PageResult;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "common-item",fallbackFactory = CommonItemFeignClientFallbackFactory.class)
public interface CommonItemFeignClient {

//    @RequestMapping("")
//    @PostMapping()
    //---------------Item
    @GetMapping("/service/item/selectTbItemAllByPage")
    PageResult selectTbItemAllByPage(@RequestParam("page") Integer page,@RequestParam("rows") Integer rows);

    @PostMapping("/service/item/insertTbItem")
    Integer insertTbItem(@RequestBody TbItem tbItem);

    @PostMapping("/service/item/deleteItemById")
    Integer deleteItemById(@RequestBody TbItem tbItem);

    @GetMapping("/service/item/findById")
    TbItem findTbItemById(@RequestParam Long itemId);

    @PostMapping("/service/item/updateItem")
    Integer updateItem(@RequestBody TbItem tbItem);

    //---------------ItemCategory
    @PostMapping("/service/itemCategory/selectItemCategoryByParentId")
    List<TbItemCat> selectItemCategoryByParentId(@RequestParam Long id);

    @GetMapping("/service/itemCategory/findTbItemCatByCid")
    TbItemCat findTbItemCatByCid(@RequestParam Long cid);

    //---------------ItemParam
    @PostMapping("/service/itemParam/selectItemParamByItemCatId")
    TbItemParam selectItemParamByItemCatId(@RequestParam Long itemCatId);

    @GetMapping("/service/itemParam/selectItemParamAll")
    PageResult selectItemParamAll(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows);

    @PostMapping("/service/itemParam/insertItemParam")
    Integer insertItemParam(@RequestBody TbItemParam itemParam);

    @PostMapping("/service/itemParam/deleteItemParamById")
    Integer deleteItemParamById(@RequestParam("id") Long id);

    //----------------ItemDesc
    @PostMapping("/service/itemDesc/insertItemDesc")
    Integer insertItemDesc(@RequestBody TbItemDesc tbItemDesc);

    @GetMapping("/service/itemDesc/findTbItemDescById")
    TbItemDesc findTbItemDescById(@RequestParam Long itemId);

    @PostMapping("/service/itemDesc/updateItemDesc")
    Integer updateItemDesc(@RequestBody TbItemDesc tbItemDesc);

    //----------------ItemParamItem
    @GetMapping("/service/itemParamItem/findTbItemParamItemByItemId")
    TbItemParamItem findTbItemParamItemByItemId(@RequestParam Long itemId);

    @PostMapping("/service/itemParamItem/updateItemParamItem")
    Integer updateItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);

    @PostMapping("/service/itemParamItem/insertTbItemParamItem")
    Integer insertTbItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);



}
