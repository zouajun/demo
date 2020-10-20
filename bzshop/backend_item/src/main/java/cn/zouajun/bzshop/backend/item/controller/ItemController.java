package cn.zouajun.bzshop.backend.item.controller;

import cn.zouajun.bzshop.backend.item.service.ItemService;
import cn.zouajun.bzshop.pojo.TbItem;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend/item")
public class ItemController {
    @Autowired
    private  ItemService itemService;

    @RequestMapping("/selectTbItemAllByPage")
    public Result selectTbItemAllByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "2") Integer rows){
        try{
            return  this.itemService.selectTbItemAllByPage(page,rows);
        }catch (Exception e){
            e.printStackTrace();

        }
        return Result.build(500,"ERROR");
    }

    @RequestMapping("/insertTbItem")
    public Result insertTbItem(TbItem tbItem,String desc,String itemParams){
        try{
            return  itemService.insertTbItem(tbItem,desc,itemParams);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"ERROR");
    }

    @RequestMapping("/deleteItemById")
    public Result deleteItemById(Long itemId){
        try{
            return itemService.deleteItemById(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"ERROR");
    }

    @RequestMapping("/preUpdateItem")
    public  Result preUpdateItem(Long itemId){
        try{
            return itemService.preUpdateItem(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"ERROR");
    }

    @RequestMapping("/updateTbItem")
    public Result updateTbItem(TbItem tbItem,String desc,String itemParam){
        try{
            return itemService.updateTbItem(tbItem,desc,itemParam);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"ERROR");
    }

}
