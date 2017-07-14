package com.sywl.web.service;

import com.sywl.web.dao.AccountInfoMapper;
import com.sywl.web.domain.GoodsDomain;
import com.sywl.web.domain.OrderDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author pengxiao
 * @date 2017/7/13
 */
@Service
public class AccountInfoService {


    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountInfoMapper accountInfoMapper;


    /**
     * 根据充值信息生成待支付订单
     *
     * @param userId
     * @param money
     * @return 待支付订单号
     */
    public String recharge(String userId, BigDecimal money) {
        //生成商品
        GoodsDomain goodsDomain = goodsService.buildRechargeGoods(userId, money);

        String goodsId = goodsDomain.getId();
        //生成待支付订单
        OrderDomain orderDomain = orderService.buildOrder(userId, goodsId, money);
        return orderDomain.getId();
    }
}
