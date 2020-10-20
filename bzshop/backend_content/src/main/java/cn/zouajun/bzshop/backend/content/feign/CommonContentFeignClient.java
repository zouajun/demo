package cn.zouajun.bzshop.backend.content.feign;

import cn.zouajun.bzshop.pojo.TbContent;
import cn.zouajun.bzshop.pojo.TbContentCategory;
import cn.zouajun.bzshop.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("common-content")
public interface CommonContentFeignClient {

//---------------------------ContentCategory
    @GetMapping("/service/contentCategory/selectContentCategoryByParentId")
    List<TbContentCategory> selectContentCategoryByParentId(@RequestParam("id") Long id);

    @PostMapping("/service/contentCategory/insertContentCategory")
    Integer insertContentCategory(@RequestBody TbContentCategory tbContentCategory);

    @PostMapping("/service/contentCategory/deleteContentCategoryById")
    Integer deleteContentCategoryById(@RequestParam("categoryId") Long categoryId);

    @PostMapping("/service/contentCategory/updateContentCategory")
    Integer updateContentCategory(@RequestBody TbContentCategory tbContentCategory);

//---------------------------Content
    @GetMapping("/service/content/selectTbContentAllByCategoryId")
    PageResult selectTbContentAllByCategoryId(@RequestParam Integer page, @RequestParam Integer rows, @RequestParam Long categoryId);

    @PostMapping("/service/content/insertTbContent")
    Integer insertTbContent(@RequestBody TbContent tbContent);

    @PostMapping("/service/content/deleteContentByIds")
    Integer deleteContentByIds(@RequestParam Long ids);
}
