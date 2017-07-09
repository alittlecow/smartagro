package com.sywl.web.service;

import com.sywl.web.dao.UserMapper;
import com.sywl.web.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
    public int insert(UserDomain userDomain){
    	return userMapper.insert(userDomain);
    };
	
	public UserDomain queryUserByName(String userName){
		return userMapper.queryUserByName(userName);
	};

	public List<UserDomain> selectAllUser(){
		return userMapper.selectAllUser();
	}


}
