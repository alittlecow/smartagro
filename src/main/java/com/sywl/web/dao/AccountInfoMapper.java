package com.sywl.web.dao;

import com.sywl.web.domain.AccountInfoDomain;
import org.apache.ibatis.annotations.Param;

/**
 * @author pengxiao
 * @date 2017/7/13
 */
public interface AccountInfoMapper {


    void save(AccountInfoDomain account);

    AccountInfoDomain queryAccountById(String accountId);

    AccountInfoDomain queryAccountByUserId(@Param("userId")String userId);

    void update(AccountInfoDomain accountInfoDomain);
}
