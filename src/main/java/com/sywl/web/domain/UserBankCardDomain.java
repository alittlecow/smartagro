package com.sywl.web.domain;

/**
 * @author Wangjp
 * @version 1.0.0
 */

/*
*       param.setBankFullName("中国银行");//银行全称，不能缩写
        param.setCardType("DE");//卡类型 DE代表借记卡，CR代表信用卡，其他值为非法
        param.setAccountType("P");//账户类型 区分对公和对私 P代表私户，C代表公户，其他值为非法
        param.setAccountNo("123456789");//收款方的银行卡号
        param.setAccountName("beecloud");//CardMapper.xml
* */
public class UserBankCardDomain {
    private String id;
    private Integer status;
    private String userId;
    private String bankFullName;
    private String cardType;
    private String accountType;
    private String accountNo;
    private String accountName;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
