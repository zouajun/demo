package cn.zouajun.bzshop.item.service.impl;

import cn.zouajun.bzshop.item.service.ItemCategoryService;
import cn.zouajun.bzshop.mapper.TbItemCatMapper;
import cn.zouajun.bzshop.pojo.TbItemCat;
import cn.zouajun.bzshop.pojo.TbItemCatExample;
import cn.zouajun.bzshop.pojo.TbItemExample;
import cn.zouajun.bzshop.utils.CatNode;
import cn.zouajun.bzshop.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    TbItemCatMapper tbItemCatMapper;

    /*
    * 根据父类id查询所有商品分类信息
    * */
    @Override
    public List<TbItemCat> selectItemCategoryByParentId(Long id) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        criteria.andStatusEqualTo(1);
        List<TbItemCat> list = this.tbItemCatMapper.selectByExample(example);
        return list;
    }

    @Override
    public TbItemCat findTbItemCatByCid(Long cid) {
        return tbItemCatMapper.selectByPrimaryKey(cid);
    }

    @Override
    public CatResult selectItemCategoryAll() {
        CatResult catResult = new CatResult();
        catResult.setData(getCatList(0L));
        return catResult;
    }


    private List<?> getCatList(long l) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(l);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        List resultList = new ArrayList();
        for (TbItemCat t:list) {
            if (t.getIsParent()){//不是叶子节点
                CatNode node = new CatNode();
                node.setName(t.getName());
                node.setItem(getCatList(t.getId()));
                resultList.add(node);
            }else{//是叶子节点
                resultList.add(t.getName());
            }
        }
        return resultList;
    }
}
