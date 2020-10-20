package cn.zouajun.bzshop.backend.item.controller;

import cn.zouajun.bzshop.backend.item.service.ItemParamService;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend/itemParam")
public class ItemParamController {

    @Autowired
    ItemParamService itemParamService;

    /*
    * 根据商品分类ID查询规格参数模板
    * */
    @RequestMapping("/selectItemParamByItemCatId/{itemCatId}")
    public Result selectItemParamByItemCatId(@PathVariable("itemCatId") Long itemCatId){
        try{
            return  itemParamService.selectItemParamByItemCatId(itemCatId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"ERROR");
    }

    @RequestMapping("/selectItemParamAll")
    public Result selectItemParamAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows){
        try{
            return  itemParamService.selectItemParamAll(page,rows);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"ERROR");
    }

    @RequestMapping("/insertItemParam")
    public  Result insertItemParam(Long itemCatId,String paramData){
        try{
            return  itemParamService.insertItemParam(itemCatId,paramData);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"ERROR");
    }

    @RequestMapping("/deleteItemParamById")
    public Result deleteItemParamById(Long id){
        try{
            return  itemParamService.deleteItemParamById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"ERROR");
    }

}
