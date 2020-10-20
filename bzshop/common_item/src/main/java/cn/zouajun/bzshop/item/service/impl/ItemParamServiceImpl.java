package cn.zouajun.bzshop.item.service.impl;

import cn.zouajun.bzshop.item.service.ItemParamService;
import cn.zouajun.bzshop.mapper.TbItemParamMapper;
import cn.zouajun.bzshop.pojo.TbItemParam;
import cn.zouajun.bzshop.pojo.TbItemParamExample;
import cn.zouajun.bzshop.utils.PageResult;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    TbItemParamMapper tbItemParamMapper;

    @Override
    public TbItemParam selectItemParamByItemCatId(Long itemCatId) {
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = tbItemParamExample.createCriteria();
        criteria.andItemCatIdEqualTo(itemCatId);
        List<TbItemParam> list= this.tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);//带有大数据类型使用该查询
        if (list!=null && list.size()>0){
            return  list.get(0);
        }
        return null;
    }

    @Override
    public PageResult selectItemParamAll(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        TbItemParamExample example = new TbItemParamExample();
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        PageInfo<TbItemParam> pageInfo =    new PageInfo<>(list);
        PageResult pageResult = new PageResult();
        pageResult.setPageIndex(page);
        pageResult.setTotalPage(pageInfo.getTotal());
        pageResult.setResult(pageInfo.getList());
        return pageResult;
    }

    @Override
    @LcnTransaction
    public Integer insertItemParam(TbItemParam itemParam) {
        return tbItemParamMapper.insertSelective(itemParam);
    }

    @Override
    @LcnTransaction
    public Integer deleteItemParamById(Long id) {
        return tbItemParamMapper.deleteByPrimaryKey(id);
    }
}
