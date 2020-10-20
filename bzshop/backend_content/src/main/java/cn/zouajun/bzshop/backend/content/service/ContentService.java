package cn.zouajun.bzshop.backend.content.service;

import cn.zouajun.bzshop.pojo.TbContent;
import cn.zouajun.bzshop.utils.Result;

public interface ContentService {

    Result selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId);

    Result insertTbContent(TbContent tbContent);

    Result deleteContentByIds(Long ids);
}
