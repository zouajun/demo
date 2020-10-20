package cn.zouajun.bzshop.backend.item.service;

import cn.zouajun.bzshop.pojo.TbItem;
import cn.zouajun.bzshop.utils.Result;

public interface ItemService {

    Result selectTbItemAllByPage(Integer page,Integer rows);

    Result insertTbItem(TbItem tbItem,String desc,String itemParams);

    Result deleteItemById(Long itemId);

    Result preUpdateItem(Long itemId);

    Result updateTbItem(TbItem tbItem, String desc, String itemParam);
}
