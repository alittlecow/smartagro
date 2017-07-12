package com.sywl.web.controller;

import com.sywl.web.domain.PayNoticeParam;
import com.sywl.web.service.CallBackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/7/12.
 */

@RestController
@RequestMapping("/beecloud")
public class CallBackController {
    private static final Logger logger = LoggerFactory.getLogger(CallBackController.class);
    @Autowired
    private CallBackService callBackService;
    @RequestMapping("/pay/notice")
    public String user(PayNoticeParam payNoticeParam) {
        logger.info(payNoticeParam.toString());
        String result = callBackService.payNotice(payNoticeParam);
        return result;
    }
}
