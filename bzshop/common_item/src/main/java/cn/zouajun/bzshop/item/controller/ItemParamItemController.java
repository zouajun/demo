package cn.zouajun.bzshop.item.controller;

import cn.zouajun.bzshop.item.service.ItemParamItemService;
import cn.zouajun.bzshop.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/itemParamItem")
public class ItemParamItemController {

    @Autowired
    ItemParamItemService itemParamItemService;

    @RequestMapping("/insertTbItemParamItem")
    public Integer insertTbItemParamItem(@RequestBody TbItemParamItem tbItemParamItem){
        return  itemParamItemService.insertTbItemParamItem(tbItemParamItem);
    }

    @RequestMapping("/findTbItemParamItemByItemId")
    public TbItemParamItem findTbItemParamItemByItemId(@RequestParam Long itemId){
        return itemParamItemService.findTbItemParamItemByItemId(itemId);
    }

    @RequestMapping("/updateItemParamItem")
    public Integer updateItemParamItem(@RequestBody TbItemParamItem tbItemParamItem){
        return itemParamItemService.updateItemParamItem(tbItemParamItem);
    }
}
