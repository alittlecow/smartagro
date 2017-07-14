package com.sywl.web.service;

import com.sywl.common.enums.Constants;
import com.sywl.utils.UUIDUtil;
import com.sywl.web.dao.OrderMapper;
import com.sywl.web.domain.OrderDomain;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author pengxiao
 * @date 2017/7/13
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;


    public OrderDomain buildOrder(String userId, String goodsId, Double orderMoney) {
        OrderDomain order = new OrderDomain();
        order.setId(UUIDUtil.getUUId());
        order.setUserId(userId);
        order.setCreateTime(new Date());
        order.setGoodsId(goodsId);
        order.setOrderMoney(orderMoney);
        order.setPayStatus(Constants.PayStatus.NEED_PAY.getValue());

        orderMapper.save(order);
        return order;
    }

    //查询订单
    public OrderDomain queryOrderById(String id) {
        if (StringUtils.isBlank(id))
            return null;
        OrderDomain orderDomain = orderMapper.queryOrderById(id);
        return orderDomain;
    }

    //更新订单
    public void update(OrderDomain orderDomain) {
        orderMapper.update(orderDomain);
    }


}
