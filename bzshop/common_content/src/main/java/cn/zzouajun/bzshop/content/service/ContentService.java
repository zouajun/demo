package cn.zzouajun.bzshop.content.service;

import cn.zouajun.bzshop.pojo.TbContent;
import cn.zouajun.bzshop.utils.PageResult;

import java.util.List;
import java.util.Map;

public interface ContentService {
    PageResult selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId);

    Integer insertTbContent(TbContent tbContent);

    Integer deleteContentByIds(Long ids);

    List<Map> selectFrontendContentByAD();
}
