package com.sywl.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sywl.annotation.Login;
import com.sywl.common.enums.Constants;
import com.sywl.support.BaseResponse;
import com.sywl.utils.RedisUtil;
import com.sywl.web.domain.*;
import com.sywl.web.service.*;
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
    private UserService userService;

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
            return new BaseResponse(Constants.ERROR, "登录失效,请重新登陆");
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
            return new BaseResponse(Constants.ERROR, "登录失效,请重新登陆");
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
            return new BaseResponse(Constants.ERROR, "登录失效,请重新登陆");
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
            return new BaseResponse(Constants.ERROR, "登录失效,请重新登陆");
        }
        UserDomain user = userService.queryUserById(userId);
        if (user == null) {
            return new BaseResponse(Constants.ERROR, "用户不存在");
        }
        //非root用户只能查看自己的流水
        if (!StringUtils.equals(user.getRoleId(), Constants.Role.ROLE_ROOT.getValue())) {
            params.put("accountId", accountInfoService.queryAccountByUserId(userId).getId());
        }
        int pageNo = MapUtils.getInteger(params, "pageNo", 1);
        int onePageNum = MapUtils.getInteger(params, "onePageNum", 10);
        PageHelper.startPage(pageNo, onePageNum);
        Page<AccountTransactionHistoryDomain> historyDomainList = (Page<AccountTransactionHistoryDomain>)
                accountTransactionHistoryService.queryAccountByCondition(params);
        return new BaseResponse(historyDomainList);
    }


    @ApiOperation(value = "账户列表", notes = "账户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "用户令牌", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pageNo", value = "页码", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "onePageNum", value = "每页数量", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "beginTime", value = "查询开始时间", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "endTime", value = "查询结束时间", required = true, dataType = "string")
    })
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @Login(needLogin = true)
    public BaseResponse list(@RequestParam Map params) {

        int pageNo = MapUtils.getInteger(params, "pageNo", 1);
        int onePageNum = MapUtils.getInteger(params, "onePageNum", 10);
        PageHelper.startPage(pageNo, onePageNum);
        Page<AccountInfoDomain> accountList = (Page<AccountInfoDomain>) accountInfoService.queryAccountByCondition(params);
        return new BaseResponse(accountList);
    }

    @ApiOperation(value = "账户信息查询(ID)", notes = "账户信息查询(ID)")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "id", value = "id", required = true, dataType = "String")

    })
    @RequestMapping(value = "queryAccountById", method = RequestMethod.POST)
    public BaseResponse queryUserAccount(String token, String id) {
        String userId = (String) redisUtil.get(token);
        if (StringUtils.isBlank(userId)) {
            return new BaseResponse(Constants.ERROR, "登录失效,请重新登陆");
        }
        AccountInfoDomain accountInfoDomain = accountInfoService.queryAccountById(id);
        return new BaseResponse(accountInfoDomain);
    }


}




























