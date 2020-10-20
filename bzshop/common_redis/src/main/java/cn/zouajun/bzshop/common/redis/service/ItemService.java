package cn.zouajun.bzshop.common.redis.service;

import cn.zouajun.bzshop.pojo.TbItem;
import cn.zouajun.bzshop.pojo.TbItemDesc;
import cn.zouajun.bzshop.pojo.TbItemParamItem;

public interface ItemService {
    void insertItemBaseInfo(TbItem tbItem);

    TbItem selectItemBaseInfo(Long tbItemId);

    void insertItemDesc(TbItemDesc tbItemDesc);

    TbItemDesc selectItemDesc(Long itemId);

    void insertItemParamItem(TbItemParamItem tbItemParamItem);

    TbItemParamItem selectItemParamItem(Long itemId);
}
