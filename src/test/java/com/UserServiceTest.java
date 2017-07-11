package com;

import com.sywl.Application;
import com.sywl.web.dao.UserMapper;
import com.sywl.web.domain.UserDomain;
import com.sywl.web.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author pengxiao
 * @date 2017/7/12
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void test() {
        UserDomain userDomain = userService.queryUserById("1");
    }
}
