package com.sywl.web.service;

import com.sywl.common.enums.Constants;
import com.sywl.support.BaseResponse;
import com.sywl.utils.RedisUtil;
import com.sywl.utils.TokenUtils;
import com.sywl.utils.UUIDUtil;
import com.sywl.web.dao.AccountInfoMapper;
import com.sywl.web.dao.UserMapper;
import com.sywl.web.domain.AccountInfoDomain;
import com.sywl.web.domain.UserDomain;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanglj on 2017/7/8.
 */

@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Autowired
    private RedisUtil redisUtil;


    public BaseResponse login(String mobile) {
        Map map = new HashMap();
        UserDomain user = userMapper.queryUserByMobile(mobile);
        if (user == null) {
            Date nowTime = new Date();
            //用户不存在 注册用户
            user = new UserDomain();
            user.setId(UUIDUtil.getUUId());
            user.setCreateTime(nowTime);
            user.setMobile(mobile);
            user.setRoleId(Constants.Role.ROLE_ORDINARY_USER.getValue());
            userMapper.save(user);
            //新建账户
            AccountInfoDomain account = new AccountInfoDomain();
            account.setBalance(new Double("0"));
            account.setCreateTime(nowTime);
            account.setUserId(user.getId());
            account.setId(UUIDUtil.getUUId());
            accountInfoMapper.save(account);

        }
        Date expiry = DateUtils.addDays(new Date(), 30);
        Key key = MacProvider.generateKey(SignatureAlgorithm.HS512);
        String jwtString = TokenUtils.getJWTString(mobile, expiry, key);
        redisUtil.set(jwtString, user.getId());
        map.put("token", jwtString);
        return new BaseResponse(map);
    }

    public int insert(UserDomain userDomain) {
        return userMapper.save(userDomain);
    }


    public UserDomain queryUserByMobile(String mobile) {
        return userMapper.queryUserByMobile(mobile);
    }


    public UserDomain queryUserById(String id) {
        return userMapper.queryUserById(id);
    }


    public BaseResponse update(UserDomain userDomain) {
        if (userDomain == null || userDomain.getId() == null)
            return new BaseResponse(BaseResponse.ERROR, "用户不存在");
        userMapper.update(userDomain);
        return new BaseResponse();
    }


    public List<UserDomain> selectAllUser() {
        return userMapper.selectAllUser();
    }


}
