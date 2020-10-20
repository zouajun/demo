package cn.zouajun.bzshop.backend.item.controller;

import cn.zouajun.bzshop.backend.item.service.ItemCategoryService;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
* 查询商品类目
* */
@RestController
@RequestMapping("/backend/itemCategory")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    @RequestMapping("/selectItemCategoryByParentId")
    public Result selectItemCategoryByParentId(@RequestParam(value = "id",defaultValue = "0") Long id){
        try{
            return  itemCategoryService.selectItemCategoryByParentId(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"ERROR");
    }

}
