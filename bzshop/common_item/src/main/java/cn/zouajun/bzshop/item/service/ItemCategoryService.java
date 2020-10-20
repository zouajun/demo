package cn.zouajun.bzshop.item.service;

import cn.zouajun.bzshop.pojo.TbItemCat;
import cn.zouajun.bzshop.pojo.TbItemDesc;
import cn.zouajun.bzshop.utils.CatResult;

import java.util.List;

public interface ItemCategoryService {

    List<TbItemCat> selectItemCategoryByParentId(Long id);

    TbItemCat findTbItemCatByCid(Long cid);

    CatResult selectItemCategoryAll();
}
