package cn.zouajun.bzshop.item.controller;

import cn.zouajun.bzshop.item.service.ItemCategoryService;
import cn.zouajun.bzshop.pojo.TbItemCat;
import cn.zouajun.bzshop.pojo.TbItemDesc;
import cn.zouajun.bzshop.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
* 商品类目
*
* */

@RestController
@RequestMapping("/service/itemCategory")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    /*
    * 根据父节点查询子节点
    * */
    @RequestMapping("/selectItemCategoryByParentId")
    public List<TbItemCat> selectItemCategoryByParentId(@RequestParam Long id){
        return  itemCategoryService.selectItemCategoryByParentId(id);
    }

    @RequestMapping("/findTbItemCatByCid")
    public TbItemCat findTbItemCatByCid(Long cid){
        return itemCategoryService.findTbItemCatByCid(cid);
    }

    @RequestMapping("/selectItemCategoryAll")
    public CatResult selectItemCategoryAll(){
        return itemCategoryService.selectItemCategoryAll();
    }
}
