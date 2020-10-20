package cn.zouajun.bzshop.backend.content.service.impl;

import cn.zouajun.bzshop.backend.content.feign.CommonContentFeignClient;
import cn.zouajun.bzshop.backend.content.service.ContentCategoryService;
import cn.zouajun.bzshop.pojo.TbContentCategory;
import cn.zouajun.bzshop.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private CommonContentFeignClient commonContentFeignClient;

    @Override
    public Result selectContentCategoryByParentId(Long id) {
        List<TbContentCategory> list = commonContentFeignClient.selectContentCategoryByParentId(id);
        if (list!=null){
            return Result.ok(list);
        }
        return Result.error("查无结果");
    }

    @Override
    @LcnTransaction
    public Result insertContentCategory(Long parentId, String name) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setName(name);
        tbContentCategory.setStatus(1);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setIsParent(false);
        Integer i = commonContentFeignClient.insertContentCategory(tbContentCategory);
        if (i != null){
            return Result.ok();
        }
        return Result.error("插入失败");
    }

    @Override
    @LcnTransaction
    public Result deleteContentCategoryById(Long categoryId) {
        Integer i = commonContentFeignClient.deleteContentCategoryById(categoryId);
        if (i == 200){
            return Result.ok();
        }
        return Result.error("删除失败");
    }

    @Override
    @LcnTransaction
    public Result updateContentCategory(Long id, String name) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(id);
        tbContentCategory.setName(name);
        Integer i = commonContentFeignClient.updateContentCategory(tbContentCategory);
        if (i!=null){
            return Result.ok();
        }
        return Result.error("修改失败");
    }
}
