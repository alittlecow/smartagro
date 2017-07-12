package com.sywl.web.service;

import com.sywl.utils.BeeCloudUtils;
import com.sywl.bean.PayNoticeParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/7/12.
 */
@Service
@Transactional
public class CallBackService {

    public String payNotice(PayNoticeParam payNoticeParam){
        String signature = payNoticeParam.getSignature();
        String transactionId = payNoticeParam.getTransactionId();
        String transactionType = payNoticeParam.getTransactionType();
        String channelType = payNoticeParam.getChannelType();
        String transactionFee = payNoticeParam.getTransactionFee();
        String toSign = BeeCloudUtils.BEECLOUD_APP_ID + transactionId +
                transactionType + channelType +
                transactionFee;
        boolean status = BeeCloudUtils.verifySign(toSign,BeeCloudUtils.BEECLOUD_MASTER_SECRET,signature);
        if (status) { //验证成功
            // TODO: 2017/7/12
            // 此处需要验证购买的产品与订单金额是否匹配:
            // Webhook传入的消息里可以根据需求自定义，具体根据后续需求确定
            // 确认支付成功后的具体业务逻辑需要根据实际情况具体确认
            return "success";
        } else { //验证失败
            return "failed";
        }
    }
}
