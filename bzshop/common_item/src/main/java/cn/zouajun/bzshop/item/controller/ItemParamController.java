package cn.zouajun.bzshop.item.controller;

import cn.zouajun.bzshop.item.service.ItemParamService;
import cn.zouajun.bzshop.pojo.TbItemParam;
import cn.zouajun.bzshop.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/itemParam")
public class ItemParamController {

    @Autowired
    ItemParamService itemParamService;

    /*
    * 根据商品分类ID查询规格参数模板
    * */
    @RequestMapping("/selectItemParamByItemCatId")
    public TbItemParam selectItemParamByItemCatId(@RequestParam Long itemCatId){

        return itemParamService.selectItemParamByItemCatId(itemCatId);
    }

    @RequestMapping("/selectItemParamAll")
    PageResult selectItemParamAll(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows){
        return itemParamService.selectItemParamAll(page,rows);
    }

    @RequestMapping("/insertItemParam")
    Integer insertItemParam(@RequestBody TbItemParam itemParam){
        return itemParamService.insertItemParam(itemParam);
    }

    @RequestMapping("/deleteItemParamById")
    Integer deleteItemParamById(@RequestParam("id") Long id){
        return  itemParamService.deleteItemParamById(id);
    }
}
