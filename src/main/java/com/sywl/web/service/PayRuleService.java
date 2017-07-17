package com.sywl.web.service;


import com.sywl.web.dao.PayRuleMapper;
import com.sywl.web.domain.PayRuleDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglj on 2017/7/17.
 */
@Service
@Transactional
public class PayRuleService {

    @Autowired
    PayRuleMapper payRuleMapper;

    public List<PayRuleDomain> queryListRule(Map<String, Object> map) {
        return payRuleMapper.queryListRule(map);
    }
}
