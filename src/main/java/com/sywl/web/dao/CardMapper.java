package com.sywl.web.dao;

import com.sywl.web.domain.CardDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * Created by zhanglj on 2017/7/17.
 */
public interface CardMapper {

    void save(CardDomain cardDomain);

    CardDomain queryCardById(@Param("id") String id);

    List<CardDomain> queryCardByCondition(Map<String, Object> params);
}
