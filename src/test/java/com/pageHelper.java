package com;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sywl.Application;
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
 * @date 2017/7/9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class pageHelper {

    @Autowired
    private UserService userService;

    @Test
    public void test2() {
        System.out.println("Test2");
    }

    @Test
    public void test() {
        PageHelper.startPage(1, 1);
        Page<UserDomain> page = (Page<UserDomain>) userService.selectAllUser();
        for (UserDomain u : page) {
            System.out.println("user:" + u.getId());
        }
        System.out.println(page.getTotal());

    }
}
