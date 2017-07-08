package com.sywl.dao;

import com.sywl.domain.UserDomain;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
/**
 * Created by zhanglj on 2017/7/8.
 */

public interface UserMapper {

	int insert(UserDomain userDomain);

	UserDomain queryUserByName(@Param("userName") String userName);

	Map queryMapByUserName(@Param("userName")String userName);
}
