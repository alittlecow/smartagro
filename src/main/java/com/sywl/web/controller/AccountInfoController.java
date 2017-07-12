package com.sywl.web.controller;

import com.sywl.support.BaseResponse;
import com.sywl.web.domain.GoodsDomain;
import com.sywl.web.service.AccountInfoService;
import com.sywl.web.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author pengxiao
 * @date 2017/7/12
 * 账户信息
 */
@RestController
@RequestMapping(value = "account")
public class AccountInfoController {

    @Autowired
    private AccountInfoService accountInfoService;




    @RequestMapping(value = "recharge")
    public BaseResponse recharge(String userId, BigDecimal money) {


        return new BaseResponse();
    }
}
