package cn.zouajun.bzshop.backend.item.service.impl;

import cn.zouajun.bzshop.backend.item.feign.CommonItemFeignClient;
import cn.zouajun.bzshop.backend.item.service.ItemParamService;
import cn.zouajun.bzshop.pojo.TbItemParam;
import cn.zouajun.bzshop.utils.PageResult;
import cn.zouajun.bzshop.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    CommonItemFeignClient commonItemFeignClient;

    @Override
    public Result selectItemParamByItemCatId(Long itemCatId) {
        TbItemParam tbItemParam = commonItemFeignClient.selectItemParamByItemCatId(itemCatId);
        if (tbItemParam!=null){
            return Result.ok(tbItemParam);
        }
        return Result.error("查无结果");
    }

    @Override
    public Result selectItemParamAll(Integer page, Integer rows) {
        PageResult pageResult = commonItemFeignClient.selectItemParamAll(page,rows);
        if (pageResult!=null){
            return Result.ok(pageResult);
        }
        return Result.error("查无结果");
    }

    @Override
    @LcnTransaction
    public Result insertItemParam(Long itemCatId, String paramData) {
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(itemCatId);
        itemParam.setParamData(paramData);
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        Integer num = commonItemFeignClient.insertItemParam(itemParam);
        if (num != null){
            return Result.ok();
        }
        return Result.error("插入失败");
    }

    @Override
    @LcnTransaction
    public Result deleteItemParamById(Long id) {
        Integer num = commonItemFeignClient.deleteItemParamById(id);
        if (num != null){
            return Result.ok();
        }
        return Result.error("删除失败");
    }
}
