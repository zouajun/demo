package cn.zouajun.bzshop.item.service;

import cn.zouajun.bzshop.pojo.TbItem;
import cn.zouajun.bzshop.utils.PageResult;

public interface ItemService {

    PageResult selectTbItemAllByPage(Integer page,Integer rows);

    Integer insertTbItem(TbItem tbItem);

    Integer updateTbItemById(TbItem tbItem);

    TbItem findById(Long itemId);
}
