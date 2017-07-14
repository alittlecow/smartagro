package com;

import com.sywl.Application;
import com.sywl.utils.UUIDUtil;
import com.sywl.web.domain.GoodsDomain;
import com.sywl.web.domain.UserDomain;
import com.sywl.web.service.GoodsService;
import com.sywl.web.service.UserService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.Date;

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

    @Autowired
    GoodsService goodsService;

    @Test
    public void test() {
        UserDomain userDomain = userService.queryUserById("1");
    }

    @Test
    public void test2(){
        GoodsDomain goodsDomain = new GoodsDomain();

        goodsService.save(goodsDomain);
    }
}
