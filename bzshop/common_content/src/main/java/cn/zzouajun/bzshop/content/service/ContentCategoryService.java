package cn.zzouajun.bzshop.content.service;

import cn.zouajun.bzshop.pojo.TbContentCategory;

import java.util.List;

public interface ContentCategoryService {

    List<TbContentCategory> selectContentCategoryByParentId(Long id);

    Integer insertContentCategory(TbContentCategory tbContentCategory);

    Integer deleteContentCategoryById(Long categoryId);

    Integer updateContentCategory(TbContentCategory tbContentCategory);
}
