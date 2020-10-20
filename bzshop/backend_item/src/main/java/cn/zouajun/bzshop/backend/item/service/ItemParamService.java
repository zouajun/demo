package cn.zouajun.bzshop.backend.item.service;

import cn.zouajun.bzshop.utils.Result;

public interface ItemParamService {

    Result selectItemParamByItemCatId(Long itemCatId);

    Result selectItemParamAll(Integer page, Integer rows);

    Result insertItemParam(Long itemCatId, String paramData);

    Result deleteItemParamById(Long id);
}
