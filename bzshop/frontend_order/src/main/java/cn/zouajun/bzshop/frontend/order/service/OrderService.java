package cn.zouajun.bzshop.frontend.order.service;

import cn.zouajun.bzshop.pojo.TbOrder;
import cn.zouajun.bzshop.pojo.TbOrderItem;
import cn.zouajun.bzshop.pojo.TbOrderShipping;
import cn.zouajun.bzshop.utils.Result;

import java.util.List;

public interface OrderService {

    Result insertOrder(List<TbOrderItem> tbOrderItem, TbOrder tbOrder, TbOrderShipping tbOrderShipping);
}
