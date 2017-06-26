package com.sywl.controller;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sywl.domain.UserDomain;
import com.sywl.service.UserService;
import com.sywl.utils.DateUtils;
import com.sywl.utils.RedisUtil;
import com.sywl.utils.TokenUtils;
import com.sywl.utils.UUIDUtil;

@RestController
@EnableAutoConfiguration
@RequestMapping("user")
public class UserController {
	
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@RequestMapping(value = "/regiest", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> regiest(@RequestParam String mobile, @RequestParam String password,@RequestParam String zone,@RequestParam String code) {
        Map<String,Object> map = new HashMap<String,Object>();
        if(mobile.equals("")||mobile==null){
        	map.put("result", "error");
        	map.put("message", "手机号码不能为空");
        	return map;
        }
        if(password.equals("")||password==null){
        	map.put("result", "error");
        	map.put("message", "密码不能为空");
        	return map;
        }
        if(zone.equals("")||zone==null){
        	map.put("result", "error");
        	map.put("message", "区号不能为空");
        	return map;
        }
        if(code.equals("")||code==null){
        	map.put("result", "error");
        	map.put("message", "验证码不能为空");
        	return map;
        }
        UserDomain user = userService.queryUserByMobile(mobile);
        if(user!=null){
        	map.put("result", "error");
        	map.put("message", "该用户已经注册，请直接登录");
        	return map;
        }
        	Date expiry = DateUtils.getExpiryDate(30 * 24 * 60);
        	Key key =MacProvider.generateKey(SignatureAlgorithm.HS512);
        	String jwtString = TokenUtils.getJWTString(mobile, expiry, key);
        	String id = UUIDUtil.getUUId();
        	UserDomain userDomain = new UserDomain();
        	userDomain.setUserId(UUIDUtil.getUUId());
        	userDomain.setUserMobile(mobile);
        	userDomain.setUserName("");
        	userDomain.setUserPwd(password);
        	userService.insert(userDomain);
        	Map userMap = userService.queryMapByUserName(mobile);
        	map.put("result", "success");
        	map.put("message", "");
        	map.put("token", jwtString);
        	map.put("body", userMap);
        	redisUtil.set(jwtString, id);
        return map;
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> login(@RequestParam String mobile, @RequestParam String password) {
        Map<String,Object> map = new HashMap<String,Object>();
        if(mobile.equals("")||mobile==null){
        	map.put("result", "error");
        	map.put("message", "手机号码不能为空");
        	return map;
        }
        if(password.equals("")||password==null){
        	map.put("result", "error");
        	map.put("message", "密码不能为空");
        	return map;
        }
        UserDomain userDomain = userService.queryUserByUserName(mobile, password);
        Map userMap = userService.queryMapByUserName(mobile);
        Date expiry = DateUtils.getExpiryDate(30 * 24 * 60);
    	Key key =MacProvider.generateKey(SignatureAlgorithm.HS512);
    	String jwtString = TokenUtils.getJWTString(mobile, expiry, key);
        if(userDomain!=null){
        	map.put("result", "success");
        	map.put("message", "");
        	map.put("token", jwtString);
        	map.put("body", userMap);
            redisUtil.set(jwtString, userDomain.getUserId());
        }else{
        	map.put("result", "error");
        	map.put("message", "用户名或密码错误");
        }
        return map;
    }
	
	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateUserInfo(String userName,String userMobile,String userMail,@RequestParam String token) {
		String userId = (String) redisUtil.get(token);
		Map<String,Object> map = new HashMap<String,Object>();
		if (userId == null || userId.equals("")) {
			map.put("result", "error");
			map.put("message", "登录已失效");
			map.put("resultCode", "0001");
		} else {
			if(userName==null){
				userName = "";
			}
			if(userMobile==null){
				userMobile = "";
			}
			if(userMail==null){
				userMail = "";
			}
			userService.updateUserById(userName, userMobile, userMail, userId);
			map.put("result", "success");
			map.put("message", "");
			map.put("resultCode", "0000");
		}
		return map;
    }

}
