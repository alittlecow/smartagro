package com.sywl.service;

import com.sywl.dao.UserMapper;
import com.sywl.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
/**
 * Created by zhanglj on 2017/7/8.
 */

@Service
@Transactional
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
    public int insert(UserDomain userDomain){
    	return userMapper.insert(userDomain);
    };
	
    public Map queryMapByUserName(String userName){
		Map userMap = userMapper.queryMapByUserName(userName);
    	return userMap;
	};
	
	public UserDomain queryUserByName(String userName){
		return userMapper.queryUserByName(userName);
	};
	
	/*public Map queryMapByUserName(String userMobile){
		return userMapper.queryMapByUserName(userMobile);
	};
	
	public int updateUserById(String userName,String userMobile,String userMail,String userId){
		return userMapper.updateUserById(userName, userMobile, userMail, userId);
	};*/
	
}
