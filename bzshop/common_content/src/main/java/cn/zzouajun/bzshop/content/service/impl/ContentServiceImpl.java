package cn.zzouajun.bzshop.content.service.impl;

import cn.zouajun.bzshop.mapper.TbContentMapper;
import cn.zouajun.bzshop.pojo.TbContent;
import cn.zouajun.bzshop.pojo.TbContentExample;
import cn.zouajun.bzshop.utils.PageResult;
import cn.zzouajun.bzshop.content.service.ContentService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper tbContentMapper;


    @Override
    public PageResult selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId) {
        PageHelper.startPage(page,rows);
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = tbContentMapper.selectByExample(example);
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        PageResult pageResult = new PageResult();
        pageResult.setPageIndex(page);
        pageResult.setTotalPage(pageInfo.getTotal());
        pageResult.setResult(pageInfo.getList());
        return pageResult;
    }

    @Override
    @LcnTransaction
    public Integer insertTbContent(TbContent tbContent) {
        tbContent.setUpdated(new Date());
        tbContent.setCreated(new Date());
        Integer i = tbContentMapper.insert(tbContent);
        return i;
    }

    @Override
    @LcnTransaction
    public Integer deleteContentByIds(Long ids) {
        return tbContentMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public List<Map> selectFrontendContentByAD() {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria= example.createCriteria();
        criteria.andCategoryIdEqualTo(89L);
        List<TbContent> tbContents = tbContentMapper.selectByExample(example);
        List<Map> list = new ArrayList<>();
        for (TbContent t:tbContents) {
            Map map = new HashMap();
            map.put("heightB",240);
            map.put("src",t.getPic());
            map.put("width",670);
            map.put("alt","banner1");
            map.put("srcB",t.getPic2());
            map.put("widthB",550);
            map.put("href","http://zouajun.com");
            map.put("height",240);
            list.add(map);
        }
        return list;
    }
}
