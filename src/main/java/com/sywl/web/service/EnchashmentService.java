package com.sywl.web.service;

import cn.beecloud.BCPay;
import cn.beecloud.bean.BCTransferParameter;
import com.sywl.exception.BusinessException;
import com.sywl.support.BaseResponse;
import com.sywl.web.dao.AccountInfoMapper;
import com.sywl.web.dao.GoodsMapper;
import com.sywl.web.dao.OrderMapper;
import com.sywl.web.domain.AccountInfoDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/7/15.
 */
@Service
@Transactional
public class EnchashmentService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AccountInfoMapper accountInfoMapper;

    public BaseResponse enchashment(BCTransferParameter enchashmentParam, String userId) {
        //TODO 根据账号信息校验用户账户资金是否充足，若不充足则返回错误金额不足
        checkAccountMoney(enchashmentParam.getTotalFee(),userId);
        //将提现订单存入提现表

        try {
            BCPay.startBCTransfer(enchashmentParam);
        }catch (Exception e) {
            return new BaseResponse<>(BaseResponse.ERROR,e.getMessage());
        }
        return new BaseResponse();
    }

    private void checkAccountMoney(Integer totalFee, String userId) {
        AccountInfoDomain  accountInfo= accountInfoMapper.queryAccountByUserId(userId);
        Double balance = accountInfo.getBalance();
        if ((balance*100) < totalFee){
            throw new BusinessException("账户资金不足");
        }
    }
}
