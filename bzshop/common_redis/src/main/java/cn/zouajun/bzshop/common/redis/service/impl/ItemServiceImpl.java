package cn.zouajun.bzshop.common.redis.service.impl;

import cn.zouajun.bzshop.common.redis.service.ItemService;
import cn.zouajun.bzshop.pojo.TbItem;
import cn.zouajun.bzshop.pojo.TbItemDesc;
import cn.zouajun.bzshop.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${frontend_item_base_info_key}")
    private String base;

    @Value("${frontend_item_desc_key}")
    private String frontend_item_desc_key;

    @Value("${frontend_item_param_key}")
    private String frontend_item_param_key;

    @Override
    public void insertItemBaseInfo(TbItem tbItem) {
        this.redisTemplate.opsForValue().set(base+":"+tbItem.getId(),tbItem);
    }

    @Override
    public TbItem selectItemBaseInfo(Long tbItemId) {
        return (TbItem) this.redisTemplate.opsForValue().get(base+":"+tbItemId);
    }

    @Override
    public void insertItemDesc(TbItemDesc tbItemDesc) {
        this.redisTemplate.opsForValue().set(frontend_item_desc_key+":"+tbItemDesc.getItemId(),tbItemDesc);
    }

    @Override
    public TbItemDesc selectItemDesc(Long itemId) {
        return (TbItemDesc) this.redisTemplate.opsForValue().get(frontend_item_desc_key+":"+itemId);
    }

    @Override
    public void insertItemParamItem(TbItemParamItem tbItemParamItem) {
        this.redisTemplate.opsForValue().set(frontend_item_param_key+":"+tbItemParamItem.getItemId(),tbItemParamItem);
    }

    @Override
    public TbItemParamItem selectItemParamItem(Long itemId) {
        return (TbItemParamItem) redisTemplate.opsForValue().get(this.frontend_item_param_key+":"+itemId);
    }
}
