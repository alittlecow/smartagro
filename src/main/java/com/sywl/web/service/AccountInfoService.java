package com.sywl.web.service;

import com.sywl.common.enums.Constants;
import com.sywl.exception.BusinessException;
import com.sywl.support.BaseResponse;
import com.sywl.utils.UUIDUtil;
import com.sywl.web.dao.AccountInfoMapper;
import com.sywl.web.dao.AccountTransactionHistoryMapper;
import com.sywl.web.dao.RootAccountTransactionMapper;
import com.sywl.web.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author pengxiao
 * @date 2017/7/13
 */
@Service
@Transactional
public class AccountInfoService {


    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Autowired
    private AccountTransactionHistoryMapper accountTransactionHistoryMapper;

    @Autowired
    private RootAccountTransactionMapper rootAccountTransactionMapper;


    /**
     * 根据充值信息生成待支付订单
     *
     * @param userId
     * @return 待支付订单号
     */
    public String recharge(String userId, String goodsId) {

        GoodsDomain goodsDomain = goodsService.queryGoodsById(goodsId);
        if (goodsDomain == null)
            throw new BusinessException("商品不存在");
        Double goodsMoney = goodsDomain.getMoney();
        //生成待支付订单
        OrderDomain orderDomain = orderService.buildOrder(userId, goodsId, goodsMoney);
        return orderDomain.getId();
    }

    /**
     * 获取账户信息
     *
     * @param accountId
     * @return
     */
    public AccountInfoDomain queryAccountById(String accountId) {
        return accountInfoMapper.queryAccountById(accountId);
    }


    /**
     * 账户列表
     * @param params
     * @return
     */
    public List<AccountInfoDomain> queryAccountByCondition(Map params) {
        return accountInfoMapper.queryAccountByCondition(params);
    }

    /**
     * 获取账户信息
     *
     * @param userId
     * @return
     */

    public AccountInfoDomain queryAccountByUserId(String userId) {
        return accountInfoMapper.queryAccountByUserId(userId);
    }


    public BaseResponse pay(String orderId, String userId) {
        OrderDomain order = orderService.queryOrderById(orderId);
        if (order == null)
            return new BaseResponse(Constants.ERROR, "订单不存在!");
        if (Constants.PayStatus.SUCCESS.getValue().compareTo(order.getPayStatus()) == 0)
            return new BaseResponse(Constants.ERROR, "订单已经支付！");
        Double orderMoney = order.getOrderMoney();
        AccountInfoDomain account = accountInfoMapper.queryAccountByUserId(userId);
        if (account == null)
            return new BaseResponse(Constants.ERROR, "用户账户不存在!");

        Double oldBalance = account.getBalance();
        Double newBalance = oldBalance - orderMoney;
        if (newBalance < 0)
            return new BaseResponse(Constants.ERROR, "账户余额不足!");

        Date nowTime = new Date();
        //订单支付成功
        order.setPayStatus(Constants.PayStatus.SUCCESS.getValue());
        order.setPayType(Constants.PayType.ACCOUNT.getValue());
        order.setPayTime(nowTime);
        orderService.update(order);
        //账户扣钱
        accountMoneyChangeRec(orderId, orderMoney, account, oldBalance, newBalance, nowTime);

        return new BaseResponse();


    }
    // 通用方法 记录 账户资金变动
    private void accountMoneyChangeRec(String orderId, Double adjustMoney, AccountInfoDomain account, Double oldBalance, Double newBalance, Date nowTime) {
        //记录本账户余额变动
        updateSelfAccount(account, newBalance, nowTime);
        //记录本账户历史
        accountTransactionHistoryRec(orderId, adjustMoney, account, oldBalance, newBalance, nowTime);
        //记录总公司账户变动
        rootAccountTransactionHistoryRec(orderId, adjustMoney, nowTime);
    }

    private void rootAccountTransactionHistoryRec(String orderId, Double adjustMoney, Date nowTime) {
        RootAccountTransactionHistoryDomain rootAccountTransactionHistoryDomain = new RootAccountTransactionHistoryDomain();
        rootAccountTransactionHistoryDomain.setId(UUIDUtil.getUUId());
        rootAccountTransactionHistoryDomain.setAdjustMoney(adjustMoney);
        rootAccountTransactionHistoryDomain.setCreateTime(nowTime);
        rootAccountTransactionHistoryDomain.setAdjustType(Constants.AccountAdjustType.USER_RECHARGE.getValue());
        rootAccountTransactionHistoryDomain.setAdjustMoney(adjustMoney);
        rootAccountTransactionHistoryDomain.setOrderId(orderId);
        rootAccountTransactionMapper.save(rootAccountTransactionHistoryDomain);
    }

    private void accountTransactionHistoryRec(String orderId, Double adjustMoney, AccountInfoDomain account, Double oldBalance, Double newBalance, Date nowTime) {
        AccountTransactionHistoryDomain accountTransactionHistoryDomain = new AccountTransactionHistoryDomain();
        accountTransactionHistoryDomain.setId(UUIDUtil.getUUId());
        accountTransactionHistoryDomain.setAccountId(account.getId());
        accountTransactionHistoryDomain.setAdjustMoney(-adjustMoney);
        accountTransactionHistoryDomain.setCreateTime(nowTime);
        accountTransactionHistoryDomain.setAdjustType(Constants.AccountAdjustType.USER_RECHARGE.getValue());
        accountTransactionHistoryDomain.setBeforeAdjustMoney(oldBalance);
        accountTransactionHistoryDomain.setAfterAdjustMoney(newBalance);
        accountTransactionHistoryDomain.setOrderId(orderId);
        accountTransactionHistoryMapper.save(accountTransactionHistoryDomain);
    }

    private void updateSelfAccount(AccountInfoDomain account, Double newBalance, Date nowTime) {
        account.setBalance(newBalance);
        account.setUpdateTime(nowTime);
        accountInfoMapper.update(account);
    }



}

























