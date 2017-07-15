package com.sywl.bean;

/**
 * Created by Administrator on 2017/7/12.
 */
public class PayNoticeParam {

    private String signature;
    private String transaction_id;
    private String transaction_type;
    private String channel_type;
    private Integer transaction_fee;
    private Boolean trade_success;
    private String message_detail;
    private String optional;
    private String sub_channel_type;
    private long timestamp;

    public Boolean getTrade_success() {
        return trade_success;
    }

    public void setTrade_success(Boolean trade_success) {
        this.trade_success = trade_success;
    }

    public String getMessage_detail() {
        return message_detail;
    }

    public void setMessage_detail(String message_detail) {
        this.message_detail = message_detail;
    }

    public String getOptional() {
        return optional;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }

    public String getSub_channel_type() {
        return sub_channel_type;
    }

    public void setSub_channel_type(String sub_channel_type) {
        this.sub_channel_type = sub_channel_type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getChannel_type() {
        return channel_type;
    }

    public void setChannel_type(String channel_type) {
        this.channel_type = channel_type;
    }

    public Integer getTransaction_fee() {
        return transaction_fee;
    }

    public void setTransaction_fee(Integer transaction_fee) {
        this.transaction_fee = transaction_fee;
    }

    @Override
    public String toString() {
        return "PayNoticeParam{" +
                "signature='" + signature + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                ", transaction_type='" + transaction_type + '\'' +
                ", channel_type='" + channel_type + '\'' +
                ", transaction_fee='" + transaction_fee + '\'' +
                '}';
    }
}
