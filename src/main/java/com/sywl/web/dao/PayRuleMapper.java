package com.sywl.web.dao;


import com.sywl.web.domain.PayRuleDomain;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglj on 2017/7/17.
 */
public interface PayRuleMapper {
    List<PayRuleDomain> queryListRule(Map<String, Object> map);
}
