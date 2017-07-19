package com.sywl.web.service;

import cn.beecloud.BCPay;
import cn.beecloud.BCUtil;
import cn.beecloud.bean.BCTransferParameter;
import com.sywl.common.dict.Consts;
import com.sywl.common.enums.Constants;
import com.sywl.exception.BusinessException;
import com.sywl.support.BaseResponse;
import com.sywl.web.dao.AccountEnchashmentMapper;
import com.sywl.web.dao.AccountInfoMapper;
import com.sywl.web.dao.UserBankCardMapper;
import com.sywl.web.domain.AccountEnchashmentDomain;
import com.sywl.web.domain.AccountInfoDomain;
import com.sywl.web.domain.UserBankCardDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/7/15.
 */
@Service
@Transactional
public class EnchashmentService {
    @Autowired
    private AccountEnchashmentMapper accountEnchashmentMapper;
    @Autowired
    private AccountInfoMapper accountInfoMapper;
    @Autowired
    private UserBankCardMapper bankCardMapper;

    public BaseResponse enchashment(BCTransferParameter enchashmentParam, String userId) {
        // 根据账号信息校验用户账户资金是否充足，若不充足则返回错误金额不足
        checkAccountMoney(enchashmentParam.getTotalFee(),userId);
        //将提现订单存入提现表
        enchashmentParam.setBillNo(saveAccountEnchashment(enchashmentParam,userId));
        try {
            BCPay.startBCTransfer(enchashmentParam);
        }catch (Exception e) {
            return new BaseResponse<>(Constants.ERROR,e.getMessage());
        }
        return new BaseResponse();
    }

    private String saveAccountEnchashment(BCTransferParameter enchashmentParam, String userId) {
        String billNo = BCUtil.generateRandomUUIDPure();
        AccountEnchashmentDomain enchashment = new AccountEnchashmentDomain();
        enchashment.setAccountName(enchashmentParam.getAccountName());
        enchashment.setAccountNo(enchashmentParam.getAccountNo());
        enchashment.setAccountType(enchashmentParam.getAccountType());
        enchashment.setStatus(Consts.WAITED_ENCHASHMENT);
        enchashment.setCreateAt(new Date());
        enchashment.setBankFullName(enchashmentParam.getBankFullName());
        enchashment.setCardType(enchashmentParam.getCardType());
        enchashment.setTotalFee(enchashmentParam.getTotalFee());
        enchashment.setUserId(userId);
        enchashment.setId(billNo);
        accountEnchashmentMapper.save(enchashment);
        return billNo;
    }

    private void checkAccountMoney(Integer totalFee, String userId) {
        AccountInfoDomain  accountInfo= accountInfoMapper.queryAccountByUserId(userId);
        Double balance = accountInfo.getBalance();
        if ((balance*100) < totalFee){
            throw new BusinessException("账户资金不足");
        }
    }

    public List<UserBankCardDomain> queryBankCardList(String userId) {
        return bankCardMapper.queryBankCardByUserId(userId);
    }

    public int updateBankCard(UserBankCardDomain bankCard) {
       return bankCardMapper.update(bankCard);
    }

    public int save(UserBankCardDomain bankCard) {
        return bankCardMapper.update(bankCard);
    }

    public void createBankCard(UserBankCardDomain bankCard, String userId) {
        bankCard.setId(UUID.randomUUID().toString().replace("-",""));
        bankCard.setUserId(userId);
        bankCardMapper.save(bankCard);
    }
}
