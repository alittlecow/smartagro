package com.sywl.web.domain;

/**
 * Created by Administrator on 2017/7/12.
 */
public class PayNoticeParam {

    private String signature;
    private String transactionId;
    private String transactionType;
    private  String channelType;
    private  String transactionFee;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(String transactionFee) {
        this.transactionFee = transactionFee;
    }

    @Override
    public String toString() {
        return "PayNoticeParam{" +
                "signature='" + signature + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", channelType='" + channelType + '\'' +
                ", transactionFee='" + transactionFee + '\'' +
                '}';
    }
}
