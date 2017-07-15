package com.sywl.web.dao;

import com.sywl.web.domain.GoodsDomain;
import com.sywl.web.domain.OrderDomain;
import org.apache.ibatis.annotations.Param;

/**
 * @author pengxiao
 * @date 2017/7/12
 */
public interface GoodsMapper {

    int save(GoodsDomain goods);
    GoodsDomain queryOrderById(@Param("id") String id);
}
