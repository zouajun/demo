package cn.zouajun.bzshop.frontend.portal.controller;

import cn.zouajun.bzshop.frontend.portal.service.ItemService;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frontend/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/selectItemInfo")
    public Result selectItemInfo(Long itemId){
        try{
            return itemService.selectItemInfo(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    @RequestMapping("/selectItemDescByItemId")
    public Result selectItemDescByItemId(@RequestParam Long itemId){
        try{
            return itemService.selectItemDescByItemId(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    @RequestMapping("/selectTbItemParamItemByItemId")
    public Result selectTbItemParamItemByItemId(@RequestParam Long itemId){
        try{
            return itemService.selectTbItemParamItemByItemId(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

}
