package com.sywl.web.controller;

import com.sywl.common.enums.Constants;
import com.sywl.support.BaseResponse;
import com.sywl.utils.*;
import com.sywl.web.common.token.authorization.manager.TokenManager;
import com.sywl.web.common.token.authorization.model.TokenModel;
import com.sywl.web.domain.UserDomain;
import com.sywl.web.service.UseRuleService;
import com.sywl.web.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhanglj on 2017/7/8.
 *
 */

@Api("与用户相关")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired

    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;
    @Autowired
    private UseRuleService useRuleService;
    @Autowired
    private TokenManager tokenManager;

    @ApiOperation(value="新增APP用户", notes="新增APP用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userName", value = "用户名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "mobile", value = "手机号", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "role", value = "角色", required = true, dataType = "String",allowableValues="admin,distributor,user,financial"),
            @ApiImplicitParam(paramType="query",name = "realName", value = "真实姓名", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "sex", value = "性别", required = false, dataType = "String",allowableValues="0,1"),
            @ApiImplicitParam(paramType="query",name = "birthday", value = "出生日期", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "email", value = "邮件", required = false, dataType = "String")
    })
    @RequestMapping(value = "/addAppUser", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> addAppUser(@RequestParam(value = "userName",required = true) String userName,
                                @RequestParam(value = "password",required = true) String password,
                                @RequestParam(value = "mobile",required = true) String mobile,
                                @RequestParam(value = "role",required = true) String role,
                                @RequestParam(value = "realName",required = false) String realName,
                                @RequestParam(value = "sex",required = false,defaultValue="0") String sex,
                                @RequestParam(value = "birthday",required = false) String birthdayStr,
                                @RequestParam(value = "email",required = false) String email) {
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
        if (!RequestParamVerifyUtils.regexMatches(Constants.PASSWORD_REGEX, password)) {
            map.put("result", "error");
            map.put("message", "密码格式错误");
            return map;
        }
        if (!(role==null) && !role.equals("") && !Constants.RoleEnum.contain(role)) {
            map.put("result", "error");
            map.put("message", "角色格式错误");
            return map;
        }
        if (!(sex==null) && !sex.equals("") && !Constants.BooleanEnum.contain(sex)) {
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

        if (!(mobile==null) && !mobile.equals("") && !RequestParamVerifyUtils.regexMatches(Constants.MOBILE_REGEX, mobile)) {
            map.put("result", "error");
            map.put("message", "手机格式错误");
            return map;
        }

        if (!(email==null) && !email.equals("") && !RequestParamVerifyUtils.regexMatches(Constants.EMAIL_REGEX, email)) {
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

        String id = UUIDUtil.getUUId();
        UserDomain userDomain = new UserDomain();
        userDomain.setId(id);
        userDomain.setUserName(userName);
        userDomain.setPassword(MD5Util.getEncryptedStr(password));
        userDomain.setRoleId(String.valueOf(Constants.RoleEnum.COMMON_USER.getIndex()));
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

        return map;
    }

    @ApiOperation(value="APP用户手机号登录(注册)", notes="APP用户使用手机号登录，如果没有注册则自动注册并登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "mobile", value = "用户手机号", required = true, dataType = "String")
    })
    @RequestMapping(value = "/APPUserLogin", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> appUserLogin(@RequestParam(value = "mobile",required = true) String mobile) {
        Map<String,Object> map = new HashMap<String,Object>();

        //非空校验
        if(RequestParamVerifyUtils.isEmpty(mobile)){
            map.put("result", "error");
            map.put("message", "用户名不能为空");
            return map;
        }

        if (!(mobile==null) && !mobile.equals("") && !RequestParamVerifyUtils.regexMatches(Constants.MOBILE_REGEX, mobile)) {
            map.put("result", "error");
            map.put("message", "手机格式错误");
            return map;
        }

        //其他校验
        UserDomain user = userService.queryUserByMobile(mobile);
        UserDomain userDomain = new UserDomain();
        if(user == null){
            String id = UUIDUtil.getUUId();
            userDomain.setId(id);
            userDomain.setRoleId(String.valueOf(Constants.RoleEnum.COMMON_USER.getIndex()));
            userDomain.setMobile(mobile);
            userDomain.setCreate_time(new Date());

            userService.insert(userDomain);
        }

        //生成令牌
        TokenModel model = tokenManager.createToken(userDomain.getId());

        map.put("result", "success");
        map.put("message", "登录成功");
        map.put("token", model.getToken());

        return map;
    }


    @ApiOperation(value="账户充值", notes="APP用户账户充值")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "id", value = "用户ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "value", value = "充值金额", required = true, dataType = "String")
    })
    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> recharge(@RequestParam(value = "id",required = true) String id,
                                                      @RequestParam(value = "value",required = true) String value) {
        //String userId = (String) redisUtil.get(token);
        Map<String,Object> map = new HashMap<String,Object>();
        if (id == null || id.equals("")) {
            map.put("result", "error");
            map.put("message", "用户ID不能为空");
        } else {
            UserDomain user = userService.queryUserById(id);
            //实际转账预留

            //在原有的账户余额基础上添加金额
            //double newValue = user.getAccountBalance() + Double.parseDouble(value);
            //userService.queryUserByName()
            //userService.updateUserById(id, null, null,null,null,null,null,null,null,newValue);
            map.put("result", "success");
            map.put("message", "账户充值成功");
        }
        return map;
    }

    @ApiOperation(value="用户退款", notes="APP用户退款")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "id", value = "用户ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "value", value = "充值金额", required = true, dataType = "String")
    })
    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> refund(@RequestParam(value = "id",required = true) String id,
                                                      @RequestParam(value = "value",required = true) String value) {
        //String userId = (String) redisUtil.get(token);
        Map<String,Object> map = new HashMap<String,Object>();
        if (id == null || id.equals("")) {
            map.put("result", "error");
            map.put("message", "用户ID不能为空");
        } else {
            UserDomain user = userService.queryUserById(id);
            /*if (Double.parseDouble(value) > user.getAccountBalance()) {
                map.put("result", "error");
                map.put("message", "用户退款失败，账户余额不足");
                return map;
            }*/
            //实际转账预留

            //在原有的账户余额基础上减金额
            //double newValue = user.getAccountBalance() - Double.parseDouble(value);
            //userService.queryUserByName()
            //userService.updateUserById(id, null, null,null,null,null,null,null,null,newValue);
            map.put("result", "success");
            map.put("message", "用户退款成功");
        }
        return map;
    }

    @ApiOperation(value="用户消费", notes="APP用户消费")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "deviceId", value = "设备ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "startTime", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "endTime", value = "结束时间", required = true, dataType = "String")
    })
    @RequestMapping(value = "/consume", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> consume(@RequestParam(value = "deviceId",required = true) String deviceId,
                                                    @RequestParam(value = "userId",required = true) String userId,
                                                     @RequestParam(value = "startTime",required = true) String startTime,
                                                     @RequestParam(value = "endTime",required = true) String endTime) {
        Map<String,Object> map = new HashMap<String,Object>();
        //参数校验
        if (!RequestParamVerifyUtils.isDateTimeFormated(startTime) || !RequestParamVerifyUtils.isDateTimeFormated(endTime)) {
            map.put("result", "error");
            map.put("message", "消费失败，时间格式错误");
            return map;
        }
        UserDomain user = userService.queryUserById(userId);
        if (user == null) {
            map.put("result", "error");
            map.put("message", "消费失败，用户不存在");
        }

        //记录设备数据到消费历史表中

        //计算消费额
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse(startTime);
            endDate = format.parse(endTime);
        } catch (ParseException e) {
            map.put("result", "error");
            map.put("message", "消费失败，时间格式错误");
            return map;
        }
        long consumeMinuts = (endDate.getTime()-startDate.getTime())/(60*1000);
        //设备计费规则
        double useRuleValue = useRuleService.queryRuleById("1").getFee();
        double consumeValue = useRuleValue * consumeMinuts;

        //实际转账预留

        //在原有的账户余额基础上减金额
        //double newValue = user.getAccountBalance() - consumeValue;
       // userService.updateUserById(userId, null, null,null,null,null,null,null,null,newValue);
        map.put("result", "success");
        map.put("message", "消费成功");
        return map;
    }

    @ApiOperation(value="申请退款", notes="用户申请将账户中的余额提现")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "userId", value = "用户名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "value", value = "金额", required = true, dataType = "String")
    })
    @RequestMapping(value = "/applyForRefund", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> applyForRefund(@RequestParam(value = "userId",required = true) String userId,
                                                      @RequestParam(value = "value",required = true) String value) {
        Map<String,Object> map = new HashMap<String,Object>();
        if (userId == null || userId.equals("")) {
            map.put("result", "error");
            map.put("message", "用户ID不能为空");
        } else {
            UserDomain user = userService.queryUserById(userId);
            /*if (Double.parseDouble(value) > user.getAccountBalance()) {
                map.put("result", "error");
                map.put("message", "用户退款失败，账户余额不足");
                return map;
            }*/
            //新增申请记录


            map.put("result", "success");
            map.put("message", "用户退款成功");
        }
        return map;
    }


    @ResponseBody
    @RequestMapping(value = "/info")
    public BaseResponse<UserDomain> queryUserById(String id) {
        UserDomain user = userService.queryUserById(id);
        return new BaseResponse<>(user);
    }


   /* @ResponseBody
    @RequestMapping(value = "/resetPassword")
    public BaseResponse resetPassword(String password, String newPassword){
        return new BaseResponse();
    }*/

}

