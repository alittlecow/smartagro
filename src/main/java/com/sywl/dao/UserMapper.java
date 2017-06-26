package com.sywl.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sywl.domain.UserDomain;

public interface UserMapper {
	
	int insert(UserDomain userDomain);
	
	UserDomain queryUserByUserName(@Param("userMobile")String userMobile,@Param("userPwd")String userPwd);

	UserDomain queryUserByMobile(@Param("userMobile")String userMobile);
	
	Map queryMapByUserName(@Param("userMobile")String userMobile);
	
	int updateUserById(@Param("userName")String userName,@Param("userMobile")String userMobile,@Param("userMail")String userMail,@Param("userId")String userId);

}
