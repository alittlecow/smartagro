package com.sywl.web.dao;

import com.sywl.web.domain.UseRuleDomain;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/7/9.
 */
public interface UseRuleMapper {

    UseRuleDomain queryRuleById(@Param("id") String id);
}
