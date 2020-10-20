package cn.zouajun.bzshop.backend.item.service.impl;

import cn.zouajun.bzshop.backend.item.feign.CommonItemFeignClient;
import cn.zouajun.bzshop.backend.item.service.ItemService;
import cn.zouajun.bzshop.pojo.TbItem;
import cn.zouajun.bzshop.pojo.TbItemCat;
import cn.zouajun.bzshop.pojo.TbItemDesc;
import cn.zouajun.bzshop.pojo.TbItemParamItem;
import cn.zouajun.bzshop.utils.IDUtils;
import cn.zouajun.bzshop.utils.PageResult;
import cn.zouajun.bzshop.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Override
    public Result selectTbItemAllByPage(Integer page, Integer rows) {

        PageResult pageResult = this.commonItemFeignClient.selectTbItemAllByPage(page,rows);
        if (pageResult != null && pageResult.getResult() != null && pageResult.getResult().size()>0){
            return  Result.ok(pageResult);
        }
        return Result.error("查无结果");
    }

    @LcnTransaction
    @Override
    public Result insertTbItem(TbItem tbItem, String desc, String itemParams) {
        // 补 齐 T b it e m 数 据
        Long itemId = IDUtils.genItemId();
        Date date = new Date();
        tbItem.setId(itemId);
        tbItem.setStatus((byte)1);
        tbItem.setUpdated(date);
        tbItem.setCreated(date);
        Integer tbItemNum = this.commonItemFeignClient.insertTbItem(tbItem);
        // 补 齐 商 品 描 述 对 象
        TbItemDesc tbItemDesc = new TbItemDesc();
        //int i = 1/0;
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        Integer tbitemDescNum = this.commonItemFeignClient.insertItemDesc(tbItemDesc);
        // 补 齐 商 品 规 格 参 数
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setUpdated(date);
        tbItemParamItem.setCreated(date);
        Integer itemParamItmeNum = this.commonItemFeignClient.insertTbItemParamItem(tbItemParamItem);
        return Result.ok ();
    }

    @Override
    @LcnTransaction
    public Result deleteItemById(Long itemId) {
        TbItem tbItem = new TbItem();
        tbItem.setId(itemId);
        tbItem.setStatus((byte)3);
        Integer i = commonItemFeignClient.deleteItemById(tbItem);
        if (i!=null){
            return Result.ok();
        }
        return Result.error("删除失败");
    }

    @Override
    public Result preUpdateItem(Long itemId) {
        //通过itemID查找Item对象
        TbItem tbItem = commonItemFeignClient.findTbItemById(itemId);
        //通过Item.cid找到itemCat对象
        TbItemCat tbItemCat = commonItemFeignClient.findTbItemCatByCid(tbItem.getCid());
        //通过ItemID查找ItemDesc对象
        TbItemDesc tbItemDesc = commonItemFeignClient.findTbItemDescById(itemId);
        //通过ItemId查找商品规格参数
        TbItemParamItem tbItemParamItem = commonItemFeignClient.findTbItemParamItemByItemId(itemId);
        Map<String,Object> map = new HashMap<>();
        map.put("itemCat",tbItemCat.getName());
        map.put("item",tbItem);
        map.put("itemDesc",tbItemDesc.getItemDesc());
        map.put("itemParamItem",tbItemParamItem);
        if (map.size()>0){
            return Result.ok(map);
        }
        return Result.error("查无结果");
    }

    @Override
    @LcnTransaction
    public Result updateTbItem(TbItem tbItem, String desc, String itemParam) {
        //更新Item
        Integer itemNum = commonItemFeignClient.updateItem(tbItem);
        //更新ItemDesc
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setItemId(tbItem.getId());
        Integer itemDescNum = commonItemFeignClient.updateItemDesc(tbItemDesc);
        //更新ItemParamItem
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(tbItem.getId());
        tbItemParamItem.setParamData(itemParam);
        Integer itemParamItemNum = commonItemFeignClient.updateItemParamItem(tbItemParamItem);
        return Result.ok();
    }
}
