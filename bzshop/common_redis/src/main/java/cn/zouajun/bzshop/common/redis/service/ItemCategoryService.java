package cn.zouajun.bzshop.common.redis.service;

import cn.zouajun.bzshop.utils.CatResult;

public interface ItemCategoryService {
    void insertItemCategory(CatResult catResult);

    CatResult selectItemCategory();
}
