package cn.zouajun.bzshop.frontend.portal.service.impl;

import cn.zouajun.bzshop.frontend.portal.feign.CommonItemFeignClient;
import cn.zouajun.bzshop.frontend.portal.feign.CommonRedisFeignClient;
import cn.zouajun.bzshop.frontend.portal.service.ItemService;
import cn.zouajun.bzshop.pojo.TbItem;
import cn.zouajun.bzshop.pojo.TbItemDesc;
import cn.zouajun.bzshop.pojo.TbItemParamItem;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CommonItemFeignClient itemFeignClient;

    @Autowired
    private CommonRedisFeignClient redisFeignClient;

    @Override
    public Result selectItemInfo(Long itemId) {
        //查询缓存
        try{
            TbItem tbItem = redisFeignClient.selectItemBaseInfo(itemId);
            if (tbItem!=null){
                return Result.ok(tbItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //查询数据库
        TbItem item = itemFeignClient.findById(itemId);
        System.out.println(item.getId());
        //存入缓存
        try {
            if (item != null){
                redisFeignClient.insertItemBaseInfo(item);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if (item!=null){
            return Result.ok(item);
        }
        return Result.error("查无结果");
    }

    @Override
    public Result selectItemDescByItemId(Long itemId) {
        //查询缓存
        try {
            TbItemDesc tbItemDesc = this.redisFeignClient.selectItemDesc(itemId);
            if (tbItemDesc!=null){
                return Result.ok(tbItemDesc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //查询数据库
        TbItemDesc tbItemDesc = itemFeignClient.selectItemDescByItemId(itemId);
        try{
            if (tbItemDesc!=null){
               this.redisFeignClient.insertItemDesc(tbItemDesc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if (tbItemDesc!=null){
            return Result.ok(tbItemDesc);
        }
        return Result.error("查无结果");
    }

    @Override
    public Result selectTbItemParamItemByItemId(Long itemId) {
        try {
            TbItemParamItem tbItemParamItem = redisFeignClient.selectItemParamItem(itemId);
            if (tbItemParamItem!=null){
                return Result.ok(tbItemParamItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbItemParamItem itemParamItem = itemFeignClient.selectTbItemParamItemByItemId(itemId);
        try{
            if (itemParamItem!=null){
                redisFeignClient.insertItemParamItem(itemParamItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if (itemParamItem != null){
            return Result.ok(itemParamItem);
        }
        return Result.error("查无结果");
    }
}
