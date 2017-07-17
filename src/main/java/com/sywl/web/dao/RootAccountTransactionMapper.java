package com.sywl.web.dao;
import com.sywl.web.domain.RootAccountTransactionHistoryDomain;

/**
 * @author pengxiao
 * @date 2017/7/13
 */
public interface RootAccountTransactionMapper {

    void save(RootAccountTransactionHistoryDomain transactionHistoryDomain);

    RootAccountTransactionHistoryDomain queryAccountById(String id);

}
