package cn.zouajun.bzshop.common.redis.controller;

import cn.zouajun.bzshop.common.redis.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/redis/content")
public class ContentController {

    @Autowired
    ContentService contentService;
    /*
    * 将大广告位的数据添加到缓存中
    * */
    @RequestMapping("/insertContentAD")
    public void insertContentAD(@RequestBody List<Map> list){
        contentService.insertContentAD(list);
    }

    @RequestMapping("/selectContentAD")
    public List<Map> selectContentAD(){
        return contentService.selectContentAD();
    }

}
