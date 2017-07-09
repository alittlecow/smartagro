package com.sywl.web.controller;

import com.sywl.common.enums.BooleanEnum;
import com.sywl.common.enums.RoleEnum;
import com.sywl.utils.*;
import com.sywl.web.domain.UserDomain;
import com.sywl.web.service.UserService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by zhanglj on 2017/7/8.
 */
@Api("与用户相关")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired

    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @ApiOperation(value="注册用户", notes="注册用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userName", value = "用户名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "role", value = "角色", required = true, dataType = "String",allowableValues="admin,distributor,user,financial"),
            @ApiImplicitParam(paramType="query",name = "realName", value = "真实姓名", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "sex", value = "性别", required = false, dataType = "String",allowableValues="0,1"),
            @ApiImplicitParam(paramType="query",name = "birthday", value = "出生日期", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "mobile", value = "手机号", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "email", value = "邮件", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "code", value = "验证码", required = false, dataType = "String")
    })
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> regiest(@RequestParam(value = "userName",required = true) String userName,
                                @RequestParam(value = "password",required = true) String password,
                                @RequestParam(value = "role",required = true) String role,
                                @RequestParam(value = "realName",required = false) String realName,
                                @RequestParam(value = "sex",required = false,defaultValue="0") String sex,
                                @RequestParam(value = "birthday",required = false) String birthdayStr,
                                @RequestParam(value = "mobile",required = false) String mobile,
                                @RequestParam(value = "email",required = false) String email,
                                @RequestParam(value = "code",required = false) String code) {
        Map<String,Object> map = new HashMap<String,Object>();

        //非空校验
        if(RequestParamVerifyUtils.isEmpty(userName)){
            map.put("result", "error");
            map.put("message", "用户名不能为空");
            return map;
        }
        if(RequestParamVerifyUtils.isEmpty(password)){
            map.put("result", "error");
            map.put("message", "密码不能为空");
            return map;
        }
        if(RequestParamVerifyUtils.isEmpty(role)){
            map.put("result", "error");
            map.put("message", "角色不能为空");
            return map;
        }
        //格式校验
        String passwordRegex = "^[a-zA-Z0-9]{6,20}$";
        if (!RequestParamVerifyUtils.regexMatches(passwordRegex, password)) {
            map.put("result", "error");
            map.put("message", "密码格式错误");
            return map;
        }
        if (!(role==null) && !role.equals("") && !RoleEnum.contain(role)) {
            map.put("result", "error");
            map.put("message", "角色格式错误");
            return map;
        }
        if (!(sex==null) && !sex.equals("") && !BooleanEnum.contain(sex)) {
            map.put("result", "error");
            map.put("message", "性别格式错误");
            return map;
        }
        Date birthday = null;
        if (!(birthdayStr==null) && !birthdayStr.equals("") && !RequestParamVerifyUtils.isDateFormated(birthdayStr)) {
            map.put("result", "error");
            map.put("message", "出生日期格式错误");
            return map;
        }
        String mobileRegex = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        if (!(mobile==null) && !mobile.equals("") && !RequestParamVerifyUtils.regexMatches(mobileRegex, mobile)) {
            map.put("result", "error");
            map.put("message", "手机格式错误");
            return map;
        }
        String emialRegex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if (!(email==null) && !email.equals("") && !RequestParamVerifyUtils.regexMatches(emialRegex, email)) {
            map.put("result", "error");
            map.put("message", "邮箱格式错误");
            return map;
        }

        //其他校验
        UserDomain user = userService.queryUserByName(userName);
        if(user!=null){
            map.put("result", "error");
            map.put("message", "该用户已经注册，请直接登录");
            return map;
        }

        Date expiry = DateUtils.getExpiryDate(30 * 24 * 60);
        Key key = MacProvider.generateKey(SignatureAlgorithm.HS512);
        String jwtString = TokenUtils.getJWTString(userName, expiry, key);
        String id = UUIDUtil.getUUId();
        UserDomain userDomain = new UserDomain();
        userDomain.setId(id);
        userDomain.setUserName(userName);
        userDomain.setPassword(MD5Util.getEncryptedStr(password));
        userDomain.setRole(role);
        userDomain.setRealName(realName);
        if (!(sex==null) && !sex.equals("")) {
            userDomain.setSex(Byte.parseByte(sex)); //如果不加非空判断可能会出现数据转换异常
        }
        userDomain.setBirthday(birthday);
        userDomain.setMobile(mobile);
        userDomain.setEmail(email);

        userService.insert(userDomain);

        map.put("result", "success");
        map.put("message", "注册成功");
        map.put("token", jwtString);
        redisUtil.set(jwtString, id);
        return map;
    }
}

