package com.sywl.web.controller;

import com.sywl.annotation.Login;
import com.sywl.support.BaseResponse;
import com.sywl.utils.RedisUtil;
import com.sywl.web.domain.AccountInfoDomain;
import com.sywl.web.domain.AccountTransactionHistoryDomain;
import com.sywl.web.domain.GoodsDomain;
import com.sywl.web.domain.OrderDomain;
import com.sywl.web.service.AccountInfoService;
import com.sywl.web.service.AccountTransactionHistoryService;
import com.sywl.web.service.GoodsService;
import com.sywl.web.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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
    private RedisUtil redisUtil;

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private AccountTransactionHistoryService accountTransactionHistoryService;

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "账户充值", notes = "账户充值")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodsId", value = "商品类型", required = true, dataType = "String")
    })
    @RequestMapping(value = "recharge", method = RequestMethod.POST)
    public BaseResponse recharge(String token, String goodsId) {
        String userId = (String) redisUtil.get(token);
        if (StringUtils.isBlank(userId)) {
            return new BaseResponse(BaseResponse.ERROR, "登录失效,请重新登陆");
        }
        String orderId = accountInfoService.recharge(userId, goodsId);
        Map map = new HashMap();
        map.put("orderId", orderId);
        return new BaseResponse(map);
    }

    @ApiOperation(value = "账户余额支付", notes = "账户余额支付")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "orderId", value = "订单ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String")

    })
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    public BaseResponse pay(String orderId, String token) {
        String userId = (String) redisUtil.get(token);
        if (StringUtils.isBlank(userId)) {
            return new BaseResponse(BaseResponse.ERROR, "登录失效,请重新登陆");
        }
        return accountInfoService.pay(orderId, userId);
    }


    @ApiOperation(value = "账户信息查询", notes = "账户信息查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String")

    })
    @RequestMapping(value = "queryUserAccount", method = RequestMethod.POST)
    public BaseResponse queryUserAccount(String token) {
        String userId = (String) redisUtil.get(token);
        if (StringUtils.isBlank(userId)) {
            return new BaseResponse(BaseResponse.ERROR, "登录失效,请重新登陆");
        }
        AccountInfoDomain accountInfoDomain = accountInfoService.queryAccountByUserId(userId);
        return new BaseResponse(accountInfoDomain);
    }


    @ApiOperation(value = "账户流水查询", notes = "账户流水查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "用户令牌", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pageNo", value = "页码", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "onePageNum", value = "每页数量", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "beginTime", value = "查询开始时间", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "endTime", value = "查询结束时间", required = true, dataType = "string")
    })
    @Login(needLogin = true)
    @RequestMapping(value = "queryUserAccountHistory", method = RequestMethod.POST)
    public BaseResponse queryUserAccountHistory(@RequestParam Map params) {
        String token = MapUtils.getString(params, "token");
        String userId = (String) redisUtil.get(token);
        if (StringUtils.isBlank(userId)) {
            return new BaseResponse(BaseResponse.ERROR, "登录失效,请重新登陆");
        }
        String accountId = accountInfoService.queryAccountByUserId(userId).getId();
        params.put("accountId", accountId);
        List<AccountTransactionHistoryDomain> historyDomainList =
                accountTransactionHistoryService.queryAccountByCondition(params);
        return new BaseResponse(historyDomainList);
    }

}
