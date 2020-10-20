package cn.zouajun.bzshop.backend.content.controller;

import cn.zouajun.bzshop.backend.content.service.ContentService;
import cn.zouajun.bzshop.pojo.TbContent;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/selectTbContentAllByCategoryId")
    public Result selectTbContentAllByCategoryId(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows, Long categoryId){
        try{
            return contentService.selectTbContentAllByCategoryId(page,rows,categoryId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    @RequestMapping("/insertTbContent")
    public  Result insertTbContent(TbContent tbContent){
        try{
            return contentService.insertTbContent(tbContent);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    @RequestMapping("/deleteContentByIds")
    public  Result deleteContentByIds(Long ids){
        try{
            return contentService.deleteContentByIds(ids);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

}
