package cn.zouajun.bzshop.item.service.impl;

import cn.zouajun.bzshop.item.service.ItemDescService;
import cn.zouajun.bzshop.mapper.TbItemDescMapper;
import cn.zouajun.bzshop.pojo.TbItemDesc;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private  TbItemDescMapper tbItemDescMapper;

    @Override
    @LcnTransaction
    public Integer insertTbItemDesc(TbItemDesc tbItemDesc) {
        return tbItemDescMapper.insert(tbItemDesc);
    }

    @Override
    public TbItemDesc findTbItemDescById(Long itemId) {
        return tbItemDescMapper.selectByPrimaryKey(itemId);
    }

    @Override
    @LcnTransaction
    public Integer updateItemDesc(TbItemDesc tbItemDesc) {
        tbItemDesc.setUpdated(new Date());
        return tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
    }
}
