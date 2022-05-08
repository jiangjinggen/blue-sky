package com.iblue.esky.service;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import com.iblue.esky.auth.user.entity.AuthUser;
import com.iblue.esky.auth.user.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>AuthUser 相关的测试</h1>
 * */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthUserTest {

    @Autowired
    private AuthUserService authUserService;

    @Test
    public void createUserRecord() {

        AuthUser authUser = new AuthUser();
        authUser.setUsername("blue001@sky.com");
        authUser.setPassword(MD5.create().digestHex("123456"));
        authUser.setExtraInfo("{}");
        authUserService.save(authUser);
        log.info("save user: [{}]",
                JSON.toJSON(authUser));
    }
}
