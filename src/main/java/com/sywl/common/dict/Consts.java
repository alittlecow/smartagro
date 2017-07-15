package com.sywl.common.dict;

/**
 * Created by Administrator on 2017/7/15.
 *
 * 保存常量的接口
 */
//为什么不用ENUMS呢，因为看到ENUMS不开心
public interface Consts {
    Byte WAITED_PAY = 1;//订单待支付状态.
    Byte WAITED_ENCHASHMENT = 0;//待提现订单
    Byte IS_PAYED = 2;//订单待支付状态
    Byte DEVICE_USETIME = 2;//设备使用时间
    Byte DEVICE_USENUM = 1;//设备使用次数
    String ENCHASHMENT_TYPE = "TRANSFER" ;
}
