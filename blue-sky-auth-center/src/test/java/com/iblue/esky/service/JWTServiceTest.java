package com.iblue.esky.service;

import com.alibaba.fastjson.JSON;
import com.iblue.esky.auth.user.service.IJWTService;
import com.iblue.esky.utils.TokenParseUtil;
import com.iblue.esky.vo.LoginUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>JWT 相关服务测试类</h1>
 * */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class JWTServiceTest {

    @Autowired
    private IJWTService ijwtService;

    @Test
    public void testGenerateAndParseToken() throws Exception {

        String jwtToken = ijwtService.generateToken(
                "blue-sky",
                "e10adc3949ba59abbe56e057f20f883e"
        );
        log.info("jwt token is: [{}]", jwtToken);

        LoginUserInfo userInfo = TokenParseUtil.parseUserInfoFromToken(jwtToken);
        log.info("parse token: [{}]", JSON.toJSONString(userInfo));
    }
}
