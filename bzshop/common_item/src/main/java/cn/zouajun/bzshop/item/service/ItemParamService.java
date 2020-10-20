package cn.zouajun.bzshop.item.service;

import cn.zouajun.bzshop.pojo.TbItemParam;
import cn.zouajun.bzshop.utils.PageResult;
import org.springframework.web.bind.annotation.RequestParam;

public  interface  ItemParamService {

    TbItemParam selectItemParamByItemCatId(@RequestParam Long itemCatId);

    PageResult selectItemParamAll(Integer page, Integer rows);

    Integer insertItemParam(TbItemParam itemParam);

    Integer deleteItemParamById(Long id);
}
