package cn.zouajun.bzshop.frontend.portal.service.impl;

import cn.zouajun.bzshop.frontend.portal.feign.CommonContentFeignClient;
import cn.zouajun.bzshop.frontend.portal.feign.CommonRedisFeignClient;
import cn.zouajun.bzshop.frontend.portal.service.ContentService;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    CommonContentFeignClient commonContentFeignClient;

    @Autowired
    CommonRedisFeignClient commonRedisFeignClient;

    @Override
    public Result selectFrontendContentByAD() {
        //查询缓存
        try{
            List<Map> list = commonRedisFeignClient.selectContentAD();
            if (list!=null && list.size()>0){
                return Result.ok(list);
            }
        }catch (Exception e){}
        //查询数据库
        List<Map> list = commonContentFeignClient.selectFrontendContentByAD();
        //将查询到的数据添加到缓存中
        try {
            if (list!=null && list.size()>0) {
                commonRedisFeignClient.insertContentAD(list);
            }
        }catch (Exception e){}

        if (list!=null && list.size()>0){
            return Result.ok(list);
        }
        return Result.error("查无结果");
    }
}
