package com.sywl.web.dao;

import com.sywl.web.domain.AccountInfoDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author pengxiao
 * @date 2017/7/13
 */
public interface AccountInfoMapper {


    void save(AccountInfoDomain account);

    AccountInfoDomain queryAccountById(String accountId);

    List<AccountInfoDomain> queryAccountByCondition(Map params);

    AccountInfoDomain queryAccountByUserId(@Param("userId") String userId);

    void update(AccountInfoDomain accountInfoDomain);
}
