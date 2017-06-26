package com.sywl.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sywl.dao.UserMapper;
import com.sywl.domain.UserDomain;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
    public int insert(UserDomain userDomain){
    	return userMapper.insert(userDomain);
    };
	
    public UserDomain queryUserByUserName(String userMobile,String userPwd){
    	UserDomain userDomain = userMapper.queryUserByUserName(userMobile, userPwd);
    	return userDomain;
	};
	
	public UserDomain queryUserByMobile(String userMobile){
		return userMapper.queryUserByMobile(userMobile);
	};
	
	public Map queryMapByUserName(String userMobile){
		return userMapper.queryMapByUserName(userMobile);
	};
	
	public int updateUserById(String userName,String userMobile,String userMail,String userId){
		return userMapper.updateUserById(userName, userMobile, userMail, userId);
	};
	
}
