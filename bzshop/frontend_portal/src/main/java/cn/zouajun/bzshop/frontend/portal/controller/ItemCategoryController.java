package cn.zouajun.bzshop.frontend.portal.controller;

import cn.zouajun.bzshop.frontend.portal.service.ItemCategoryService;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frontend/itemCategory")
public class ItemCategoryController {

    @Autowired
    ItemCategoryService itemCategoryService;

    @RequestMapping("/selectItemCategoryAll")
    public Result selectItemCategoryAll(){
        try{
            return itemCategoryService.selectItemCategoryAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  Result.build(500,"error");
    }

}
