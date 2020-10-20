package cn.zzouajun.bzshop.content.service.impl;

import cn.zouajun.bzshop.mapper.TbContentCategoryMapper;
import cn.zouajun.bzshop.pojo.TbContentCategory;
import cn.zouajun.bzshop.pojo.TbContentCategoryExample;
import cn.zzouajun.bzshop.content.service.ContentCategoryService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<TbContentCategory> selectContentCategoryByParentId(Long id) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        return list;
    }


    @Override
    @LcnTransaction
    public Integer insertContentCategory(TbContentCategory tbContentCategory) {
        tbContentCategory.setUpdated(new Date());
        tbContentCategory.setCreated(new Date());
        Integer num = tbContentCategoryMapper.insert(tbContentCategory);
        TbContentCategory paren = tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
        if (!paren.getIsParent()){
            paren.setIsParent(true);
            paren.setUpdated(new Date());
            this.tbContentCategoryMapper.updateByPrimaryKey(paren);
        }
        return num;
    }

    @Override
    @LcnTransaction
    public Integer deleteContentCategoryById(Long categoryId) {
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(categoryId);
        //删除当前节点
        deletNode(tbContentCategory);
        //判断其父节点是否存在其他子节点
        TbContentCategory parent = tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parent.getId());
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        if (list.size()==0){
            parent.setIsParent(false);
            parent.setUpdated(new Date());
            tbContentCategoryMapper.updateByPrimaryKey(parent);
        }
        return 200;
    }

    @Override
    @LcnTransaction
    public Integer updateContentCategory(TbContentCategory tbContentCategory) {
        tbContentCategory.setUpdated(new Date());
        Integer integer = tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
        return integer;
    }

    private void deletNode(TbContentCategory tbContentCategory) {
        if (tbContentCategory.getIsParent()){
            //删除子节点
            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(tbContentCategory.getId());
            List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
            for (TbContentCategory t:list) {
                deletNode(t);
            }
        }
        //删除
        tbContentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
    }
}
