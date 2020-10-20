package cn.zouajun.bzshop.frontend.portal.service;

import cn.zouajun.bzshop.utils.Result;

public interface ItemService {
    Result selectItemInfo(Long itemId);

    Result selectItemDescByItemId(Long itemId);

    Result selectTbItemParamItemByItemId(Long itemId);
}
