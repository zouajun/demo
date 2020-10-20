package cn.zzouajun.bzshop.content.controller;

import cn.zouajun.bzshop.pojo.TbContent;
import cn.zouajun.bzshop.utils.PageResult;
import cn.zzouajun.bzshop.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/selectTbContentAllByCategoryId")
    PageResult selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId){
        return contentService.selectTbContentAllByCategoryId(page,rows,categoryId);
    }

    @RequestMapping("/insertTbContent")
    Integer insertTbContent(@RequestBody TbContent tbContent){
        return contentService.insertTbContent(tbContent);
    }

    @RequestMapping("/deleteContentByIds")
    Integer deleteContentByIds(@RequestParam Long ids){
        return contentService.deleteContentByIds(ids);
    }

    @RequestMapping("/selectFrontendContentByAD")
    List<Map> selectFrontendContentByAD(){
        return contentService.selectFrontendContentByAD();
    }

}
