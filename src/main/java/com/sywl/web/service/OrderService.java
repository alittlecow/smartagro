package com.sywl.web.service;

import com.sywl.common.enums.Constants;
import com.sywl.utils.UUIDUtil;
import com.sywl.web.dao.OrderMapper;
import com.sywl.web.domain.OrderDomain;
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



    public OrderDomain buildOrder(String userId, String goodsId,BigDecimal orderMoney) {
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


}
