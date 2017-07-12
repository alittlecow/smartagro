package com.sywl.web.service;

import com.sywl.web.dao.GoodsMapper;
import com.sywl.web.domain.GoodsDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pengxiao
 * @date 2017/7/12
 * 商品管理
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public int save(GoodsDomain goods) {
        return goodsMapper.save(goods);
    }


    private GoodsDomain buildGoods() {

        return null;
    }
}
