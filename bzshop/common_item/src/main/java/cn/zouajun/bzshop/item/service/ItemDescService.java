package cn.zouajun.bzshop.item.service;

import cn.zouajun.bzshop.pojo.TbItemDesc;

public interface ItemDescService {

    Integer insertTbItemDesc(TbItemDesc tbItemDesc);

    TbItemDesc findTbItemDescById(Long itemId);

    Integer updateItemDesc(TbItemDesc tbItemDesc);
}
