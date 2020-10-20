package cn.zouajun.bzshop.frontend.order.service.impl;

import cn.zouajun.bzshop.frontend.order.feign.CommonRedisFeignClient;
import cn.zouajun.bzshop.frontend.order.feign.FrontendCartFeignClient;
import cn.zouajun.bzshop.frontend.order.service.OrderService;
import cn.zouajun.bzshop.mapper.TbOrderItemMapper;
import cn.zouajun.bzshop.mapper.TbOrderMapper;
import cn.zouajun.bzshop.mapper.TbOrderShippingMapper;
import cn.zouajun.bzshop.pojo.TbOrder;
import cn.zouajun.bzshop.pojo.TbOrderItem;
import cn.zouajun.bzshop.pojo.TbOrderShipping;
import cn.zouajun.bzshop.utils.IDUtils;
import cn.zouajun.bzshop.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;
    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;
    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;
    @Autowired
    private FrontendCartFeignClient frontendCartFeignClient;

    @Override
    @LcnTransaction
    public Result insertOrder(List<TbOrderItem> tbOrderItem, TbOrder tbOrder, TbOrderShipping tbOrderShipping) {
        //获取订单ID
        Long orderId = commonRedisFeignClient.selectOrderId();
        //补齐tbOrder
        tbOrder.setOrderId(orderId.toString());
        tbOrder.setStatus(1);
        Date date = new Date();
        tbOrder.setCreateTime(date);
        tbOrder.setUpdateTime(date);
        //0未评价，1已评价
        tbOrder.setBuyerRate(0);
        this.tbOrderMapper.insert(tbOrder);
        //插入订单中所包含的商品
        for (TbOrderItem item:tbOrderItem) {
            item.setId(IDUtils.genItemId()+"");
            item.setOrderId(orderId.toString());
            this.tbOrderItemMapper.insert(item);
            this.frontendCartFeignClient.deleteItemFromCart(Long.parseLong(item.getItemId()),tbOrder.getUserId().toString());
        }
        tbOrderShipping.setOrderId(orderId.toString());
        tbOrderShipping.setCreated(date);
        tbOrderShipping.setUpdated(date);
        this.tbOrderShippingMapper.insert(tbOrderShipping);
        return Result.ok(orderId);
    }
}
