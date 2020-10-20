package cn.zzouajun.bzshop.content.controller;

import cn.zouajun.bzshop.pojo.TbContentCategory;
import cn.zzouajun.bzshop.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/contentCategory")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/selectContentCategoryByParentId")
    List<TbContentCategory> selectContentCategoryByParentId(@RequestParam(defaultValue = "0") Long id){
        return contentCategoryService.selectContentCategoryByParentId(id);
    }

    @RequestMapping("/insertContentCategory")
    Integer insertContentCategory(@RequestBody TbContentCategory tbContentCategory){
        return contentCategoryService.insertContentCategory(tbContentCategory);
    }

    @PostMapping("/deleteContentCategoryById")
    Integer deleteContentCategoryById(@RequestParam("categoryId") Long categoryId){
        return contentCategoryService.deleteContentCategoryById(categoryId);
    }

    @RequestMapping("/updateContentCategory")
    Integer updateContentCategory(@RequestBody TbContentCategory tbContentCategory){
        return contentCategoryService.updateContentCategory(tbContentCategory);
    }

}
