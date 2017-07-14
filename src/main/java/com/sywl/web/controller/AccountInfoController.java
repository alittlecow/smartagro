package com.sywl.web.controller;

import com.sywl.support.BaseResponse;
import com.sywl.web.domain.GoodsDomain;
import com.sywl.web.service.AccountInfoService;
import com.sywl.web.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @ApiOperation(value = "账户充值", notes = "账户充值")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "money", value = "充值金额", required = true, dataType = "double"),

    })
    @RequestMapping(value = "recharge")
    public BaseResponse recharge(String userId, BigDecimal money) {
        String orderId = accountInfoService.recharge(userId, money);
        Map map = new HashMap();
        map.put("orderId", orderId);
        return new BaseResponse(map);
    }
}
