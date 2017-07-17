package com.sywl.web.dao;

import com.sywl.web.domain.UserDomain;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * Created by zhanglj on 2017/7/8.
 */

public interface UserMapper {

	int insert(UserDomain userDomain);

	UserDomain queryUserByName(@Param("userName") String userName);

	UserDomain queryUserByMobile(@Param("mobile") String mobile);

	UserDomain queryUserById(@Param("id") String id);

	int updateUserById(@Param("userId")String userId, @Param("userName")String userName, @Param("password")String password,
					   @Param("role")String role, @Param("realName")String realName, @Param("sex")Byte sex,
					   @Param("birthday")Date birthday, @Param("mobile")String mobile, @Param("email")String email,
					   @Param("accountBalance")double accountBalance);

	List<UserDomain> selectAllUser();

}
