package com.sywl.web.service;

import com.sywl.web.dao.UserBankCardMapper;
import com.sywl.web.domain.UserBankCardDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Wangjp
 * @version 1.0.0
 */
@Service
public class BankCardService {
    @Autowired
    private UserBankCardMapper bankCardMapper;

    public List<UserBankCardDomain> queryBankCardList(String userId) {
        return bankCardMapper.queryBankCardByUserId(userId);
    }

    public int updateBankCard(UserBankCardDomain bankCard) {
        return bankCardMapper.update(bankCard);
    }

    public void createBankCard(UserBankCardDomain bankCard, String userId) {
        bankCard.setId(UUID.randomUUID().toString().replace("-",""));
        bankCard.setUserId(userId);
        bankCardMapper.save(bankCard);
    }
}
