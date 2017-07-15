package com.sywl.exception;

import com.sywl.support.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengxiao
 * @date 2017/7/15
 * 全局异常处理
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);


    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public BaseResponse BusinessExceptionHandler(BusinessException e) {
        logger.error(e.getMessage());
        return new BaseResponse(BaseResponse.ERROR, e.getErrorMsg());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse ExceptionHandler(Exception e) {
        logger.error(e.getMessage());
        return new BaseResponse(BaseResponse.ERROR, "服务异常");
    }

}
