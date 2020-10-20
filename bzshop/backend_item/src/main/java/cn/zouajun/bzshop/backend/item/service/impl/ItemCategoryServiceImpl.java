package cn.zouajun.bzshop.backend.item.service.impl;

import cn.zouajun.bzshop.backend.item.feign.CommonItemFeignClient;
import cn.zouajun.bzshop.backend.item.service.ItemCategoryService;
import cn.zouajun.bzshop.pojo.TbItemCat;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {


    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Override
    public Result selectItemCategoryByParentId(Long id) {
        List<TbItemCat> list = commonItemFeignClient.selectItemCategoryByParentId(id);
        if (list!=null&&list.size()>0){
            return Result.ok(list);
        }
        return Result.error("查无结果");
    }
}
