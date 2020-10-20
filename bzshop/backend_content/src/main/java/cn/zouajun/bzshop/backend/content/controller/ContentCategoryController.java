package cn.zouajun.bzshop.backend.content.controller;

import cn.zouajun.bzshop.backend.content.service.ContentCategoryService;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/selectContentCategoryByParentId")
    public Result selectContentCategoryByParentId(Long id){
        try{
            return contentCategoryService.selectContentCategoryByParentId(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    @RequestMapping("/insertContentCategory")
    public Result insertContentCategory(Long parentId,String name){
        try{
            return contentCategoryService.insertContentCategory(parentId,name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    @RequestMapping("/deleteContentCategoryById")
    public Result deleteContentCategoryById(Long categoryId){
        try{
            return contentCategoryService.deleteContentCategoryById(categoryId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    @RequestMapping("/updateContentCategory")
    public Result updateContentCategory(Long id,String name){
        try{
            return contentCategoryService.updateContentCategory(id,name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

}
