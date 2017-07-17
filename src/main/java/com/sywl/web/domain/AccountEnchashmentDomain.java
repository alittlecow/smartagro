package com.sywl.web.domain;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/15.
 */
public class AccountEnchashmentDomain {
    // 商户订单号 8到32位数字和/或字母组合同一订单号不可重复提交
    private String id;
    //账户userId
    private String userId;
    // 下发订单总金额 必须是正整数，单位为分
    private Integer totalFee;
   // 银行全名
    private String bankFullName;
    // 银行卡类型 区分借记卡和信用卡 DE代表借记卡，CR代表信用卡，其他值为非法
    private String cardType;
    // 收款帐户类型 区分对公和对私 P代表私户，C代表公户，其他值为非法
    private String accountType;
    // 收款方的银行卡号
    private String accountNo;
    // 收款方的姓名或者单位名
    private String accountName;
    //提现状态
    private Byte status;
    //创建时间
    private Date createAt;
    //支付时间
    private Date payAt;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getPayAt() {
        return payAt;
    }

    public void setPayAt(Date payAt) {
        this.payAt = payAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getBankFullName() {
        return bankFullName;
    }

    public void setBankFullName(String bankFullName) {
        this.bankFullName = bankFullName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
