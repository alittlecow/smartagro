package com.sywl.web.dao;

import com.sywl.web.domain.UserDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * Created by zhanglj on 2017/7/8.
 */

public interface UserMapper {

	int insert(UserDomain userDomain);

	UserDomain queryUserByName(@Param("userName") String userName);

	List<UserDomain> selectAllUser();

}
