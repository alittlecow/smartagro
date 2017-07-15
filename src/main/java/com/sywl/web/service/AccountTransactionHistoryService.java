package com.sywl.web.service;

import com.sywl.web.dao.AccountTransactionHistoryMapper;
import com.sywl.web.domain.AccountTransactionHistoryDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author pengxiao
 * @date 2017/7/15
 */
@Service
public class AccountTransactionHistoryService {


    @Autowired
    private AccountTransactionHistoryMapper accountTransactionHistoryMapper;



    public List<AccountTransactionHistoryDomain> queryAccountByCondition(Map params) {
        return accountTransactionHistoryMapper.queryAccountByCondition(params);
    }
}
