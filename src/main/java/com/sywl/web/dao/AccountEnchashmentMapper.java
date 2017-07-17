package com.sywl.web.dao;

import com.sywl.web.domain.AccountEnchashmentDomain;

/**
 * Created by Administrator on 2017/7/15.
 */
public interface AccountEnchashmentMapper {

    void save(AccountEnchashmentDomain enchashment);

    AccountEnchashmentDomain queryAccountById(String id);
}
