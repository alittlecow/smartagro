package com.sywl.web.service;

import com.sywl.utils.BeeCloudUtils;
import com.sywl.bean.PayNoticeParam;
import com.sywl.web.dao.GoodsMapper;
import com.sywl.web.dao.OrderMapper;
import com.sywl.web.domain.GoodsDomain;
import com.sywl.web.domain.OrderDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.sywl.common.dict.Consts.*;

/**
 * Created by Administrator on 2017/7/12.
 */
@Service
@Transactional
public class CallBackService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    public String payNotice(PayNoticeParam payNoticeParam){
        String signature = payNoticeParam.getSignature();
        String transactionId = payNoticeParam.getTransaction_id();
        String transactionType = payNoticeParam.getTransaction_type();
        String channelType = payNoticeParam.getChannel_type();
        Integer transactionFee = payNoticeParam.getTransaction_fee();
        String toSign = BeeCloudUtils.BEECLOUD_APP_ID + transactionId +
                transactionType + channelType +
                transactionFee;
        boolean status = BeeCloudUtils.verifySign(toSign,BeeCloudUtils.BEECLOUD_TEST_SECRET,signature);
        if (status) { //验证成功
            // TODO: 2017/7/12
            // 此处需要验证购买的产品与订单金额是否匹配:
            // Webhook传入的消息里可以根据需求自定义，具体根据后续需求确定
            // 确认支付成功后的具体业务逻辑需要根据实际情况具体确认
            // 验证成功即证明回调无误，若后续业务逻辑出现异常
            // 则返回failed，beecloud将会在接下来的1小时内重复发起回调请求
            handleCallBackOrder(transactionId,payNoticeParam);
            return "success";
        } else {
            //验证失败
            //这里的验证失败是指参数校验失败，若代码配置无误，则怀疑参数遭到篡改，返回failed，
            //beecloud将会在接下来的1小时内重复发起回调请求，
            return "failed";
        }
    }

    private void handleCallBackOrder(String id, PayNoticeParam payNoticeParam) {
        OrderDomain order = orderMapper.queryOrderById(id);
        GoodsDomain goodsDomain =  goodsMapper.queryGoodsById(order.getGoodsId());
        String transactionType = payNoticeParam.getTransaction_type();
        //交易成功即修改订单状态为已支付状态
        changeOrderStatus(payNoticeParam, order);


        if (ENCHASHMENT_TYPE.equals(transactionType))
            //若订单为提现订单
            //TODO 提现订单
            return;
        Byte goodsType = goodsDomain.getType();


        if(DEVICE_USE_ORDER.equals(goodsType) || ID_CARD_RECHARGE.equals(goodsType)){
            //若订单为设备使用订单或id卡充值订单
            //TODO 业务逻辑 修改设备信息等操作
            //记录账户分成
            orderFeeRecord(payNoticeParam);
        }

        if(ACCOUNT_RECHARGE.equals(goodsType)){
            //个人账户充值记录
            accountRechargeRec(payNoticeParam);
         }

    }

    /*修改个人账户，记录个人账户流水*/
    private void accountRechargeRec(PayNoticeParam payNoticeParam) {

    }

    /*
    账户分成 由两个部分组成（是否还包含 公司分成 存疑）
    1门店分成 2经销商分成
    根据订单金额以及提成比例 分别修改对应账户的账户余额
    同时记录账户余额变化流水
    */
    private void orderFeeRecord(PayNoticeParam payNoticeParam) {
        //1 门店分成
        String orderId = payNoticeParam.getTransaction_id();

        //2 经销商分成
    }

    private void changeOrderStatus(PayNoticeParam payNoticeParam, OrderDomain order) {
        order.setPayStatus(IS_PAYED);
        order.setPayType(payNoticeParam.getPayType());
        order.setPayTime(new Date());
        orderMapper.update4CallBack(order);
    }
}
