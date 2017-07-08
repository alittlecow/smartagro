package com.sywl.controller;

import com.sywl.domain.UserDomain;
import com.sywl.service.UserService;
import com.sywl.utils.DateUtils;
import com.sywl.utils.RedisUtil;
import com.sywl.utils.TokenUtils;
import com.sywl.utils.UUIDUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
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
            @ApiImplicitParam(paramType="query",name = "userName", value = "用户名称", required = true, dataType = "String",defaultValue="defaultValue01",access="access01",example="example01"),
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
        if(userName.equals("")||userName==null){
            map.put("result", "error");
            map.put("message", "用户名不能为空");
            return map;
        }
        if(password.equals("")||password==null){
            map.put("result", "error");
            map.put("message", "密码不能为空");
            return map;
        }
        if(role.equals("")||role==null){
            map.put("result", "error");
            map.put("message", "角色不能为空");
            return map;
        }
        //格式校验
        String passwordRegex = "^\\d{6,18}$";
        if (!Pattern.matches(passwordRegex, password)) {
            map.put("result", "error");
            map.put("message", "密码长度错误");
            return map;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = format.parse(birthdayStr);
        } catch (ParseException e) {
            e.printStackTrace();
            map.put("result", "error");
            map.put("message", "出生日期格式错误");
            return map;
        }

        //内容校验
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
        userDomain.setId(UUIDUtil.getUUId());
        userDomain.setUserName(userName);
        userDomain.setPassword(password);
        userDomain.setRole(role);
        userDomain.setRealName(realName);
        userDomain.setSex(Byte.parseByte(sex));
        userDomain.setBirthday(birthday);
        userDomain.setMobile(mobile);
        userDomain.setEmail(email);
        userService.insert(userDomain);
        Map userMap = userService.queryMapByUserName(userName);
        map.put("result", "success");
        map.put("message", "注册成功");
        map.put("token", jwtString);
        map.put("body", userMap);
        redisUtil.set(jwtString, id);
        return map;
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UserDomain")
    @RequestMapping(value="", method=RequestMethod.POST)
    public String postUser(@RequestBody UserDomain user) {
        System.out.print(user.getUserName());
        return "success";
    }
}
