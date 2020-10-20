package cn.zouajun.bzshop.backend.content.service.impl;

import cn.zouajun.bzshop.backend.content.feign.CommonContentFeignClient;
import cn.zouajun.bzshop.backend.content.service.ContentService;
import cn.zouajun.bzshop.pojo.TbContent;
import cn.zouajun.bzshop.utils.PageResult;
import cn.zouajun.bzshop.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private CommonContentFeignClient commonContentFeignClient;

    @Override
    public Result selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId) {
        PageResult pageResult = commonContentFeignClient.selectTbContentAllByCategoryId(page,rows,categoryId);
        if (pageResult!=null && pageResult.getResult().size()>0){
            return Result.ok(pageResult);
        }
        return Result.error("查无结果");
    }

    @Override
    @LcnTransaction
    public Result insertTbContent(TbContent tbContent) {
        Integer i = commonContentFeignClient.insertTbContent(tbContent);
        if (i != null){
            return Result.ok();
        }
        return Result.error("插入失败");
    }

    @Override
    @LcnTransaction
    public Result deleteContentByIds(Long ids) {
        Integer i = commonContentFeignClient.deleteContentByIds(ids);
        if (i != null){
            return  Result.ok();
        }
        return Result.error("删除失败");
    }
}
