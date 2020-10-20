package cn.zouajun.bzshop.common.redis.controller;

import cn.zouajun.bzshop.common.redis.service.ItemCategoryService;
import cn.zouajun.bzshop.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* 缓存首页商品分类
* */
@RestController
@RequestMapping("/redis/itemCategory")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    /*
    * 向Redis中添加缓存数据
    * */
    @RequestMapping("/insertItemCategory")
    public void insertItemCategory(@RequestBody CatResult catResult){
        itemCategoryService.insertItemCategory(catResult);
    }

    /*
    * 查询Redis中的商品缓存数据
    * */
    @RequestMapping("/selectItemCategory")
    public CatResult selectItemCategory(){
        return itemCategoryService.selectItemCategory();
    }

}
