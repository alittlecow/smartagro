package com.sywl.web.service;

import com.sywl.web.dao.UseRuleMapper;
import com.sywl.web.domain.UseRuleDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Zhanglj on 2017/7/9.
 */
@Service
@Transactional
public class UseRuleService {
    @Autowired
    private UseRuleMapper useRuleMapper;

    public UseRuleDomain queryRuleById(String id){
        return useRuleMapper.queryRuleById(id);
    };

}
