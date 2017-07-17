package com.sywl.web.controller;

import com.sywl.annotation.Login;
import com.sywl.common.enums.Constants;
import com.sywl.support.BaseResponse;
import com.sywl.utils.RedisUtil;
import com.sywl.utils.RequestParamVerifyUtils;
import com.sywl.utils.SMSUtils;
import com.sywl.web.domain.AccountInfoDomain;
import com.sywl.web.domain.UserDomain;
import com.sywl.web.service.UseRuleService;
import com.sywl.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhanglj on 2017/7/8.
 */

@Api("用户信息")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UseRuleService useRuleService;

//    @ApiOperation(value = "注册用户", notes = "注册用户")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query", name = "userName", value = "用户名称", required = true, dataType = "String"),
//            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String"),
//            @ApiImplicitParam(paramType = "query", name = "mobile", value = "手机号", required = false, dataType = "String"),
//            @ApiImplicitParam(paramType = "query", name = "realName", value = "真实姓名", required = false, dataType = "String"),
//            @ApiImplicitParam(paramType = "query", name = "sex", value = "性别", required = false, dataType = "String", allowableValues = "0,1"),
//            @ApiImplicitParam(paramType = "query", name = "birthday", value = "出生日期", required = false, dataType = "String"),
//            @ApiImplicitParam(paramType = "query", name = "email", value = "邮件", required = false, dataType = "String")
//    })
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public BaseResponse register(@RequestParam(value = "userName", required = true) String userName,
//                                 @RequestParam(value = "password", required = true) String password,
//                                 @RequestParam(value = "mobile", required = true) String mobile,
//                                 @RequestParam(value = "realName", required = false) String realName,
//                                 @RequestParam(value = "sex", required = false, defaultValue = "0") String sex,
//                                 @RequestParam(value = "birthday", required = false) String birthdayStr,
//                                 @RequestParam(value = "email", required = false) String email) {
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        //非空校验
//        if (RequestParamVerifyUtils.isEmpty(userName)) {
//            return new BaseResponse(BaseResponse.ERROR, "用户名不能为空");
//        }
//        if (RequestParamVerifyUtils.isEmpty(password)) {
//            return new BaseResponse(BaseResponse.ERROR, "密码不能为空");
//        }
//        //格式校验
//        String passwordRegex = "^[a-zA-Z0-9]{6,20}$";
//        if (!RequestParamVerifyUtils.regexMatches(passwordRegex, password)) {
//            return new BaseResponse(BaseResponse.ERROR, "密码格式错误");
//        }
//        if (!(sex == null) && !sex.equals("") && !BooleanEnum.contain(sex)) {
//            return new BaseResponse(BaseResponse.ERROR, "性别格式错误");
//        }
//        Date birthday = null;
//        if (!(birthdayStr == null) && !birthdayStr.equals("") && !RequestParamVerifyUtils.isDateFormated(birthdayStr)) {
//            return new BaseResponse(BaseResponse.ERROR, "出生日期格式错误");
//        }
//        String mobileRegex = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
//        if (!(mobile == null) && !mobile.equals("") && !RequestParamVerifyUtils.regexMatches(mobileRegex, mobile)) {
//            return new BaseResponse(BaseResponse.ERROR, "手机格式错误");
//        }
//        String emialRegex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
//        if (!(email == null) && !email.equals("") && !RequestParamVerifyUtils.regexMatches(emialRegex, email)) {
//            return new BaseResponse(BaseResponse.ERROR, "邮箱格式错误");
//        }
//        //其他校验
//        UserDomain user = userService.queryUserByName(userName);
//        if (user != null) {
//            return new BaseResponse(BaseResponse.ERROR, "该用户已经注册，请直接登录");
//        }
//
//        Date expiry = DateUtils.getExpiryDate(30 * 24 * 60);
//        Key key = MacProvider.generateKey(SignatureAlgorithm.HS512);
//        String jwtString = TokenUtils.getJWTString(userName, expiry, key);
//        String id = UUIDUtil.getUUId();
//        UserDomain userDomain = new UserDomain();
//        userDomain.setId(id);
//        userDomain.setUserName(userName);
//        userDomain.setPassword(MD5Util.getEncryptedStr(password));
//        userDomain.setRole(Constants.ROLE_ORDINARY_USER);
//        userDomain.setRealName(realName);
//        if (!(sex == null) && !sex.equals("")) {
//            userDomain.setSex(Byte.parseByte(sex)); //如果不加非空判断可能会出现数据转换异常
//        }
//        userDomain.setBirthday(birthday);
//        userDomain.setMobile(mobile);
//        userDomain.setEmail(email);
//
//        userService.save(userDomain);
//
//        map.put("token", jwtString);
//        redisUtil.set(jwtString, id);
//        return new BaseResponse<>();
//    }


    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "mobile", value = "手机号码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "verifyCode", value = "短信验证码", required = true, dataType = "String")
    })
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResponse login(String mobile, String verifyCode) {
        if (!SMSUtils.isValid(mobile, verifyCode)) {
            return new BaseResponse(Constants.ERROR, "验证码错误");
        }
        return userService.login(mobile);
    }

    @ApiOperation(value = "token测试", notes = "token测试")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String")
    })
    @RequestMapping(value = "token", method = RequestMethod.POST)
    public BaseResponse token(String token) {
        String userId = (String) redisUtil.get(token);
        Map map = new HashMap();
        map.put("userId", userId);
        return new BaseResponse(map);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public BaseResponse update(UserDomain userDomain) {
        return userService.update(userDomain);
    }

    @RequestMapping(value = "queryUserById", method = {RequestMethod.POST, RequestMethod.GET})
    public BaseResponse<UserDomain> queryUserById(String id) {
        UserDomain user = userService.queryUserById(id);
        return new BaseResponse<>(user);
    }

    @Login(needLogin = true)
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    public BaseResponse resetPassword(String token,String newPassword) {
        String userId = (String) redisUtil.get(token);
        if (StringUtils.isBlank(userId)) {
            return new BaseResponse(Constants.ERROR, "登录失效,请重新登陆");
        }
        if (!RequestParamVerifyUtils.regexMatches(Constants.PASSWORD_REGEX, newPassword)) {
           return new BaseResponse(Constants.ERROR, "修改失败，密码格式错误");
        }
        UserDomain dbUser = userService.queryUserById(userId);
        dbUser.setPassword(newPassword);
        userService.update(dbUser);
        return new BaseResponse(Constants.SUCCESS, "密码修改成功");
    }

}

