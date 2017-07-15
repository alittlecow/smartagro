package com.sywl.web.controller;

import com.sywl.support.BaseResponse;
import com.sywl.web.domain.AccountInfoDomain;
import com.sywl.web.domain.GoodsDomain;
import com.sywl.web.domain.OrderDomain;
import com.sywl.web.service.AccountInfoService;
import com.sywl.web.service.GoodsService;
import com.sywl.web.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pengxiao
 * @date 2017/7/12
 * 账户信息
 */
@Api("账户管理")
@RestController
@RequestMapping(value = "account")
public class AccountInfoController {

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "账户充值", notes = "账户充值")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "money", value = "充值金额", required = true, dataType = "double"),
            @ApiImplicitParam(paramType = "query", name = "goodsId", value = "商品类型", required = true, dataType = "String")

    })
    @RequestMapping(value = "recharge", method = RequestMethod.POST)
    public BaseResponse recharge(String userId, Double money, String goodsId) {
        String orderId = accountInfoService.recharge(userId, money, goodsId);
        Map map = new HashMap();
        map.put("orderId", orderId);
        return new BaseResponse(map);
    }

    @ApiOperation(value = "账户余额支付", notes = "账户余额支付")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "orderId", value = "订单ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID", required = true, dataType = "String")

    })
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    public BaseResponse pay(String orderId, String userId) {
        return accountInfoService.pay(orderId, userId);
    }


    @ApiOperation(value = "账户信息查询", notes = "账户信息查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID", required = true, dataType = "String")

    })
    @RequestMapping(value = "queryUserAccount", method = RequestMethod.POST)
    public BaseResponse queryUserAccount(String userId) {
        AccountInfoDomain accountInfoDomain = accountInfoService.queryAccountByUserId(userId);
        return new BaseResponse(accountInfoDomain);
    }

}
