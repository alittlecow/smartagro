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
    public BaseResponse  enchashment(@RequestBody BCTransferParameter enchashmentParam) {
        logger.info(enchashmentParam.toString());
        BaseResponse result = enchashmentService.enchashment(enchashmentParam);
        return result;
    }
}



