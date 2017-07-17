package com.sywl.web.controller;

import cn.beecloud.bean.BCTransferParameter;
import com.sywl.support.BaseResponse;
import com.sywl.web.service.EnchashmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/7/15.
 */
@RestController
@RequestMapping("/beecloud")
public class EnchashmentController {
    private static final Logger logger = LoggerFactory.getLogger(EnchashmentController.class);
    @Autowired
    private EnchashmentService enchashmentService;

    @RequestMapping("/pay/enchashment")
    public BaseResponse  enchashment(@RequestBody BCTransferParameter enchashmentParam,String userId) {
        /*
        param.setBillNo("1111111111");//设置订单号 8到32位数字和/或字母组合，请自行确保在商户系统中唯一，同一订单号不可重复提交，否则会造成订单重复
        param.setTitle("subject");//设置标题 UTF8编码格式，32个字节内，最长支持16个汉字
        param.setTotalFee(1);//设置下发订单总金额 必须是正整数，单位为分
        param.setTradeSource("OUT_PC");//UTF8编码格式，目前只能填写OUT_PC
        param.setBankFullName("中国银行");//银行全称，不能缩写
        param.setCardType("DE");//卡类型 DE代表借记卡，CR代表信用卡，其他值为非法
        param.setAccountType("P");//账户类型 区分对公和对私 P代表私户，C代表公户，其他值为非法
        param.setAccountNo("123456789");//收款方的银行卡号
        param.setAccountName("beecloud");//收款方的姓名或者单位名
        * */
        logger.info(enchashmentParam.toString());
        enchashmentParam.setTradeSource("OUT_PC");
        enchashmentParam.setTitle("提现操作");
        BaseResponse result = enchashmentService.enchashment(enchashmentParam,userId);
        return result;
    }
}



