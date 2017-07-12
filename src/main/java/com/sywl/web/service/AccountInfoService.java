package com.sywl.web.service;

import com.sywl.web.dao.AccountInfoMapper;
import com.sywl.web.domain.GoodsDomain;
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

    public void recharge(String userId, BigDecimal money) {
        GoodsDomain goodsDomain = goodsService.build(userId, money);
        String goodsId = goodsDomain.getId();
    }
}
