package com.sywl.web.dao;

import com.sywl.web.domain.UserBankCardDomain;

import java.util.List;

/**
 * @author Wangjp
 * @version 1.0.0
 */
public interface UserBankCardMapper {

    UserBankCardDomain queryBankCardById(String id);

    List<UserBankCardDomain> queryBankCardByUserId(String userId);

    int save(UserBankCardDomain userBankCardDomain);

    int update(UserBankCardDomain userBankCardDomain);
}
