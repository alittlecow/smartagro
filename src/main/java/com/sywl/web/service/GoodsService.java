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


    public GoodsDomain build(String userId, BigDecimal money) {
        GoodsDomain goods = buildRechargeGoods(userId, money);
        save(goods);
        return goods;
    }


    /**
     * 生成充值商品
     *
     * @return
     */
    public GoodsDomain buildRechargeGoods(String userId, BigDecimal costMoney) {
        GoodsDomain goods = new GoodsDomain();
        goods.setUserId(userId);
        goods.setStartTime(new Date());
        goods.setType(Constants.GoodsType.ACCOUNT_RECHARGE.getValue());
        goods.setCostMoney(costMoney);
        goods.setId(UUIDUtil.getUUId());
        return goods;
    }

    /**
     * 生成设备使用结算商品
     *
     * @return
     */
    public GoodsDomain buildDeviceUseGoods(String userId, BigDecimal costMoney, String deviceId) {
        GoodsDomain goods = new GoodsDomain();
        goods.setUserId(userId);
        goods.setStartTime(new Date());
        goods.setType(Constants.GoodsType.DEVICE_USE.getValue());
        goods.setDeviceId(deviceId);
        goods.setCostMoney(costMoney);
        goods.setId(UUIDUtil.getUUId());
        return goods;
    }

}



























