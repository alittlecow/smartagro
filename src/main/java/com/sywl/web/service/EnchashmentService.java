package com.sywl.web.service;

import cn.beecloud.BCPay;
import cn.beecloud.bean.BCTransferParameter;
import com.sywl.support.BaseResponse;

/**
 * Created by Administrator on 2017/7/15.
 */
public class EnchashmentService {
    public BaseResponse enchashment(BCTransferParameter enchashmentParam) {
        try {
            BCPay.startBCTransfer(enchashmentParam);
        }catch (Exception e) {
            return new BaseResponse<>(BaseResponse.ERROR,e.getMessage());
        }

        return null;
    }
}
