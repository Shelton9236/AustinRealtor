package com.ece656.house.web;

import com.ece656.house.biz.service.RecommendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.ece656.house.biz.service.UserService;
import com.ece656.house.common.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class AuthTest {
    @Autowired
    private UserService userService;

    /*@Test
    public void testAuth() {
        User user = userService.auth("jgcs12345@163.com", "1234567");
        assert user != null;
        System.out.println(user.getAboutme());
    }*/

    @Autowired
    private RecommendService recommendService;

    @Test
    public void test()
    {
        System.out.println(recommendService.getPercentage(10));
    }
}
