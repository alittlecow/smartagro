package com.sywl.web.dao;

import com.sywl.web.domain.OrderDomain;
import org.apache.ibatis.annotations.Param;

/**
 * @author pengxiao
 * @date 2017/7/13
 */
public interface OrderMapper {


    void save(OrderDomain order);

    OrderDomain queryOrderById(@Param("id") String id);

    void update(OrderDomain orderDomain);

    void update4CallBack(OrderDomain orderDomain);
}
