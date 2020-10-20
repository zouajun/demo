package cn.zouajun.bzshop.item.controller;

import cn.zouajun.bzshop.item.service.ItemService;
import cn.zouajun.bzshop.pojo.TbItem;
import cn.zouajun.bzshop.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController//相当于@ResponseBody(响应json字符串) and @Controller
@RequestMapping("/service/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /*
    * 查询商品数据
    * */
    @RequestMapping("/selectTbItemAllByPage")
    public PageResult selectTbItemAllByPage(@RequestParam Integer page,@RequestParam Integer rows){
        return  itemService.selectTbItemAllByPage(page,rows);
    }

    /*
    * 商品的添加
    * */
    @RequestMapping("/insertTbItem")
    public Integer insertTbItem(@RequestBody TbItem tbItem){
        return this.itemService.insertTbItem(tbItem);
    }

    /*
    *删除商品，商品状态改为3
    * */
    @RequestMapping("/deleteItemById")
    public Integer deleteItemById(@RequestBody TbItem tbItem){
        return itemService.updateTbItemById(tbItem);
    }

    @RequestMapping("/findById")
    public TbItem findById(@RequestParam Long itemId){
        return itemService.findById(itemId);
    }

    @RequestMapping("/updateItem")
    public Integer updateItem(@RequestBody TbItem tbItem){
        return itemService.updateTbItemById(tbItem);
    }


}
