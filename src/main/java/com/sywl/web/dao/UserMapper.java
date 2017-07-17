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

    int save(UserDomain userDomain);

    UserDomain queryUserByMobile(@Param("mobile") String mobile);

    UserDomain queryUserById(@Param("id") String id);

    int update(UserDomain userDomain);

    List<UserDomain> selectAllUser();


}
