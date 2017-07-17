package com.sywl.web.common.token.controller;


import com.sywl.web.common.token.authorization.annotation.Authorization;
import com.sywl.web.common.token.authorization.annotation.CurrentUser;
import com.sywl.web.common.token.authorization.manager.TokenManager;
import com.sywl.web.common.token.authorization.model.TokenModel;
import com.sywl.web.common.token.config.ResultStatus;
import com.sywl.web.common.token.model.ResultModel;
import com.sywl.web.domain.UserDomain;
import com.sywl.web.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取和删除token的请求地址，在Restful设计中其实就对应着登录和退出登录的资源映射
 *
 * Created by zhanglj on 2017/7/17.
 */
@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResultModel> login(@RequestParam String mobile) {

        UserDomain user = userService.queryUserByMobile(mobile);

        //生成一个token，保存用户登录状态
        TokenModel model = tokenManager.createToken(user.getId());
        return new ResponseEntity<>(ResultModel.ok(model), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Authorization
    public ResponseEntity<ResultModel> logout(@CurrentUser UserDomain user) {
        tokenManager.deleteToken(user.getId());
        return new ResponseEntity<>(ResultModel.ok(), HttpStatus.OK);
    }

}
