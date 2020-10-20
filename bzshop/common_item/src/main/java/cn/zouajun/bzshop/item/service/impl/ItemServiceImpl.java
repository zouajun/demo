package cn.zouajun.bzshop.item.service.impl;

import cn.zouajun.bzshop.item.service.ItemService;
import cn.zouajun.bzshop.mapper.TbItemMapper;
import cn.zouajun.bzshop.pojo.TbItem;
import cn.zouajun.bzshop.pojo.TbItemExample;
import cn.zouajun.bzshop.utils.PageResult;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private  TbItemMapper tbItemMapper;

    /*
    * 查询所有商品并分页
    * */
    @Override
    public PageResult selectTbItemAllByPage(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        TbItemExample tbItemExample = new TbItemExample();
        TbItemExample.Criteria criteria = tbItemExample.createCriteria();
        criteria.andStatusEqualTo((byte) 1);
        List<TbItem> list = this.tbItemMapper.selectByExample(tbItemExample);
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        PageResult result = new PageResult();
        result.setPageIndex(page);//当前页
        result.setTotalPage(pageInfo.getTotal());
        result.setResult(list);
        return result;
    }

    /*
    * 持久化TbItem
    * */
    @Override
    @LcnTransaction
    public Integer insertTbItem(TbItem tbItem) {
        return this.tbItemMapper.insert(tbItem);
    }

    @Override
    @LcnTransaction
    public Integer updateTbItemById(TbItem tbItem) {
        tbItem.setUpdated(new Date());
        return tbItemMapper.updateByPrimaryKeySelective(tbItem);
    }

    @Override
    public TbItem findById(Long itemId) {
        return tbItemMapper.selectByPrimaryKey(itemId);
    }
}
