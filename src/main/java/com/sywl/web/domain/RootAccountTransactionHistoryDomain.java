package com.sywl.web.domain;

import java.util.Date;

/**
 * @author pengxiao
 * @date 2017/7/14
 */
public class RootAccountTransactionHistoryDomain {
    private String id;
    private double adjustMoney;
    private Byte adjustType;
    private Date createTime;
    private String orderId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAdjustMoney() {
        return adjustMoney;
    }

    public void setAdjustMoney(double adjustMoney) {
        this.adjustMoney = adjustMoney;
    }

    public Byte getAdjustType() {
        return adjustType;
    }

    public void setAdjustType(Byte adjustType) {
        this.adjustType = adjustType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
