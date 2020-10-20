package cn.zouajun.bzshop.item.service;

import cn.zouajun.bzshop.pojo.TbItemParamItem;

public interface ItemParamItemService {

    Integer insertTbItemParamItem(TbItemParamItem tbItemParamItem);

    TbItemParamItem findTbItemParamItemByItemId(Long itemId);

    Integer updateItemParamItem(TbItemParamItem tbItemParamItem);
}
