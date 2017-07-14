package com.sywl.web.domain;

import java.util.Date;

/**
 * @author pengxiao
 * @date 2017/7/14
 */
public class AccountTransactionHistoryDomain {
    private String id;
    private String accountId;
    private double beforeAdjustMoney;
    private double adjustMoney;
    private double afterAdjustMoney;
    private Byte adjustType;
    private Date createTime;
    private String orderId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBeforeAdjustMoney() {
        return beforeAdjustMoney;
    }

    public void setBeforeAdjustMoney(double beforeAdjustMoney) {
        this.beforeAdjustMoney = beforeAdjustMoney;
    }

    public double getAdjustMoney() {
        return adjustMoney;
    }

    public void setAdjustMoney(double adjustMoney) {
        this.adjustMoney = adjustMoney;
    }

    public double getAfterAdjustMoney() {
        return afterAdjustMoney;
    }

    public void setAfterAdjustMoney(double afterAdjustMoney) {
        this.afterAdjustMoney = afterAdjustMoney;
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
