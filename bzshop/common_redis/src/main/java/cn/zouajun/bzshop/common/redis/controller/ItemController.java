package cn.zouajun.bzshop.common.redis.controller;

import cn.zouajun.bzshop.common.redis.service.ItemService;
import cn.zouajun.bzshop.pojo.TbItem;
import cn.zouajun.bzshop.pojo.TbItemDesc;
import cn.zouajun.bzshop.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/item")
public class ItemController {

    @Autowired
    private  ItemService itemService;

    /*
    * 缓存商品基本信息
    * */
    @RequestMapping("/insertItemBaseInfo")
    public void insertItemBaseInfo(@RequestBody TbItem tbItem){
        System.out.println(tbItem.getId());
        itemService.insertItemBaseInfo(tbItem);
    }

    /*
    * 查询缓存商品基本信息
    * */
    @RequestMapping("/selectItemBaseInfo")
    public TbItem selectItemBaseInfo(@RequestParam Long tbItemId){
        return itemService.selectItemBaseInfo(tbItemId);
    }

    /*
    * 缓存商品介绍信息desc
    * */
    @RequestMapping("/insertItemDesc")
    public void insertItemDesc(@RequestBody TbItemDesc tbItemDesc){
        this.itemService.insertItemDesc(tbItemDesc);
    }

    @RequestMapping("/selectItemDesc")
    public TbItemDesc selectItemDesc(@RequestParam Long itemId){
        return this.itemService.selectItemDesc(itemId);
    }

    @RequestMapping("/insertItemParamItem")
    public void insertItemParamItem(@RequestBody TbItemParamItem tbItemParamItem){
        this.itemService.insertItemParamItem(tbItemParamItem);
    }

    @RequestMapping("/selectItemParamItem")
    public TbItemParamItem selectItemParamItem(@RequestParam Long itemId){
        return this.itemService.selectItemParamItem(itemId);
    }


}
