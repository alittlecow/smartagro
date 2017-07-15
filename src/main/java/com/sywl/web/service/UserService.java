package com.sywl.web.service;

import com.sywl.common.enums.Constants;
import com.sywl.support.BaseResponse;
import com.sywl.utils.UUIDUtil;
import com.sywl.web.dao.UserMapper;
import com.sywl.web.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by zhanglj on 2017/7/8.
 */

@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public BaseResponse login(String mobile) {
        UserDomain user = userMapper.queryUserByMobile(mobile);
        if (user == null) {
            //用户不存在 注册用户
            UserDomain userDomain = new UserDomain();
            userDomain.setId(UUIDUtil.getUUId());
            userDomain.setCreateTime(new Date());
            userDomain.setMobile(mobile);
            userDomain.setRoleId(Constants.Role.ROLE_ORDINARY_USER.getValue());
            userMapper.save(userDomain);
        } else {
            //用户存在 登陆
            // TODO: 2017/7/15 登陆

        }
        return new BaseResponse();
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
