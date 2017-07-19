package com.sywl.web.dao;

import com.sywl.web.domain.ApplyCardDomain;

import java.util.Map;

/**
 * @author pengxiao
 * @date 2017/7/19
 */
public interface ApplyCardMapper {

    void save(ApplyCardDomain applyCardDomain);

    void update(ApplyCardDomain applyCardDomain);

    ApplyCardDomain queryById(String id);

    ApplyCardDomain queryByCondition(Map params);


}
