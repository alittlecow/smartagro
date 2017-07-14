package com.sywl.web.dao;


import com.sywl.web.domain.AccountTransactionHistoryDomain;

/**
 * @author pengxiao
 * @date 2017/7/13
 */
public interface AccountTransactionHistoryMapper {


    void save(AccountTransactionHistoryDomain transactionHistoryDomain);

    AccountTransactionHistoryDomain queryAccountById(String accountId);

}
