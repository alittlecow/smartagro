package com.sywl.web.service;

import com.sywl.common.enums.Constants;
import com.sywl.utils.UUIDUtil;
import com.sywl.web.dao.GoodsMapper;
import com.sywl.web.domain.GoodsDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author pengxiao
 * @date 2017/7/12
 * 商品管理
 */
@Service
@Transactional
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public int save(GoodsDomain goods) {
        return goodsMapper.save(goods);
    }

}



























