package cn.zouajun.bzshop.frontend.portal.service.impl;

import cn.zouajun.bzshop.frontend.portal.feign.CommonItemFeignClient;
import cn.zouajun.bzshop.frontend.portal.feign.CommonRedisFeignClient;
import cn.zouajun.bzshop.frontend.portal.service.ItemCategoryService;
import cn.zouajun.bzshop.utils.CatResult;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    CommonItemFeignClient commonItemFeignClient;

    @Autowired
    CommonRedisFeignClient commonRedisFeignClient;


    @Override
    public Result selectItemCategoryAll() {
        //查询缓存
        try{
            CatResult catResult = this.commonRedisFeignClient.selectItemCategory();
            //判断缓存是否命中
            if (catResult!=null && catResult.getData() != null && catResult.getData().size()>0){
                return Result.ok(catResult);
            }
        }catch (Exception e){}
        //查询数据库
        CatResult catResult = commonItemFeignClient.selectItemCategoryAll();
        //添加到缓存
        try{
            if (catResult!=null && catResult.getData() != null && catResult.getData().size()>0){
                commonRedisFeignClient.insertItemCategory(catResult);
            }
        }catch (Exception e){}
        if (catResult!=null && catResult.getData() != null && catResult.getData().size()>0){
            return Result.ok(catResult);
        }
        return Result.error("查无结果");
    }
}
