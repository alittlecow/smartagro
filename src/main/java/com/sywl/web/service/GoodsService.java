package com.sywl.web.service;

import com.sywl.common.enums.Constants;
import com.sywl.exception.BusinessException;
import com.sywl.utils.SywlStringUtils;
import com.sywl.utils.UUIDUtil;
import com.sywl.web.dao.GoodsMapper;
import com.sywl.web.domain.GoodsDomain;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public int save(String type, String name, String value, String money) {
        if (!SywlStringUtils.checkAll(type, name, value, money)) {
            throw new BusinessException("商品信息不完整");
        }
        GoodsDomain goods = new GoodsDomain();
        goods.setId(UUIDUtil.getUUId());
        goods.setCreateTime(new Date());
        goods.setStatus(Constants.BasicType.ENABLE.getValue());
        goods.setType(new Byte(type));
        goods.setMoney(new Double(money));
        goods.setName(name);
        goods.setValue(new Integer(value));
        return goodsMapper.save(goods);
    }

    public void update(GoodsDomain goodsDomain) {
        if (goodsDomain.getId() == null) {
            throw new BusinessException("商品不存在");
        }
        goodsMapper.update(goodsDomain);
    }

    public void deleteBatch(List<String> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return;
        }
        goodsMapper.deleteBatch(idList);
    }

    public GoodsDomain queryGoodsById(String id) {
        return goodsMapper.queryGoodsById(id);
    }

    public List<GoodsDomain> queryGoodsByCondition(Map params) {
        return goodsMapper.queryGoodsByCondition(params);
    }

}



























