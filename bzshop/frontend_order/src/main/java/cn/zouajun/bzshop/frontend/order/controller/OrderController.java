package cn.zouajun.bzshop.frontend.order.controller;

import cn.zouajun.bzshop.frontend.order.service.OrderService;
import cn.zouajun.bzshop.pojo.TbOrder;
import cn.zouajun.bzshop.pojo.TbOrderItem;
import cn.zouajun.bzshop.pojo.TbOrderShipping;
import cn.zouajun.bzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/insertOrder")
    private Result insertOrder(String orderItem, TbOrder tbOrder, TbOrderShipping tbOrderShipping){
        try {
            List<TbOrderItem> tbOrderItem = (List<TbOrderItem>) (Result.formatObjectToList(orderItem, TbOrderItem.class).getData());
            return orderService.insertOrder(tbOrderItem,tbOrder,tbOrderShipping);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }
}
