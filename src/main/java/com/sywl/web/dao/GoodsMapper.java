package com.sywl.web.dao;

import com.sywl.web.domain.GoodsDomain;
import com.sywl.web.domain.OrderDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author pengxiao
 * @date 2017/7/12
 */
public interface GoodsMapper {

    int save(GoodsDomain goods);

    GoodsDomain queryGoodsById(@Param("id") String id);

    List<GoodsDomain> queryGoodsByCondition(Map params);

    void update(GoodsDomain goods);

    void deleteBatch(List<String> list);
}
