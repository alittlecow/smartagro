package com.sywl.controller;

import com.sywl.domain.UserDomain;
import com.sywl.service.UserService;
import com.sywl.utils.DateUtils;
import com.sywl.utils.RedisUtil;
import com.sywl.utils.TokenUtils;
import com.sywl.utils.UUIDUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhanglj on 2017/7/8.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @ApiOperation(value="注册普通用户", notes="新增普通用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "role", value = "角色", required = true, dataType = "String"),
            @ApiImplicitParam(name = "realName", value = "真实姓名", required = false, dataType = "String"),
            @ApiImplicitParam(name = "sex", value = "性别", required = false, dataType = "Byte"),
            @ApiImplicitParam(name = "age", value = "年龄", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "mobile", value = "手机号", required = false, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "邮件", required = false, dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = false, dataType = "String")
    })
    @RequestMapping(value = "/regiestUser", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> regiest(@RequestParam(value = "userName",required = true) String userName,
                                @RequestParam(value = "password",required = true) String password,
                                @RequestParam(value = "role",required = true) String role,
                                @RequestParam(value = "realName",required = false) String realName,
                                @RequestParam(value = "sex",required = false) byte sex,
                                @RequestParam(value = "age",required = false) int age,
                                @RequestParam(value = "mobile",required = false) String mobile,
                                @RequestParam(value = "email",required = false) String email,
                                @RequestParam(value = "code",required = false) String code) {
        Map<String,Object> map = new HashMap<String,Object>();
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
        userDomain.setId(Long.parseLong(UUIDUtil.getUUId()));
        userDomain.setUserName(userName);
        userDomain.setPassword(password);
        userDomain.setRole(role);
        userDomain.setRealName(realName);
        userDomain.setSex(sex);
        userDomain.setAge(age);
        userDomain.setMobile(mobile);
        userDomain.setEmail(email);
        userService.insert(userDomain);
        Map userMap = userService.queryMapByUserName(userName);
        map.put("result", "success");
        map.put("message", "");
        map.put("token", jwtString);
        map.put("body", userMap);
        redisUtil.set(jwtString, id);
        return map;
    }



    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "success";
    }
}

