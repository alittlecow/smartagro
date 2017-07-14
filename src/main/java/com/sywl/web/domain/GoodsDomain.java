package com.sywl.web.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author pengxiao
 * @date 2017/7/12
 */
public class GoodsDomain {
    private String id;
    private Byte type;
    private Date createTime;
    private BigDecimal money;
    private BigDecimal value;
    private Byte status;


    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
