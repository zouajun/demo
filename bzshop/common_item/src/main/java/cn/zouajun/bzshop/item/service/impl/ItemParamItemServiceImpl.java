package cn.zouajun.bzshop.item.service.impl;

import cn.zouajun.bzshop.item.service.ItemParamItemService;
import cn.zouajun.bzshop.mapper.TbItemParamItemMapper;
import cn.zouajun.bzshop.pojo.TbItemParamItem;
import cn.zouajun.bzshop.pojo.TbItemParamItemExample;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Autowired
    TbItemParamItemMapper tbItemParamItemMapper;

    /*
    * 添加商品模板
    * */
    @Override
    @LcnTransaction
    public Integer insertTbItemParamItem(TbItemParamItem tbItemParamItem) {
        return tbItemParamItemMapper.insert(tbItemParamItem);
    }

    @Override
    public TbItemParamItem findTbItemParamItemByItemId(Long itemId) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    @LcnTransaction
    public Integer updateItemParamItem(TbItemParamItem tbItemParamItem) {
        tbItemParamItem.setUpdated(new Date());
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(tbItemParamItem.getItemId());
        return tbItemParamItemMapper.updateByExampleSelective(tbItemParamItem,example);
    }
}
