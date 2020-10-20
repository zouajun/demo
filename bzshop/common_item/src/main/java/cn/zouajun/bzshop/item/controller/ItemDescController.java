package cn.zouajun.bzshop.item.controller;

import cn.zouajun.bzshop.item.service.ItemDescService;
import cn.zouajun.bzshop.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/itemDesc")
public class ItemDescController {

    @Autowired
    private  ItemDescService itemDescService;

    /*
    * 添加商品描述
    * */
    @RequestMapping("/insertItemDesc")
    public Integer insertTbItemDesc(@RequestBody TbItemDesc tbItemDesc){
        return itemDescService.insertTbItemDesc(tbItemDesc);
    }

    @RequestMapping("/findTbItemDescById")
    public TbItemDesc findTbItemDescById(@RequestParam Long itemId){
        return itemDescService.findTbItemDescById(itemId);
    }

    @RequestMapping("/updateItemDesc")
    public Integer updateItemDesc(@RequestBody TbItemDesc tbItemDesc){
        return itemDescService.updateItemDesc(tbItemDesc);
    }

}
