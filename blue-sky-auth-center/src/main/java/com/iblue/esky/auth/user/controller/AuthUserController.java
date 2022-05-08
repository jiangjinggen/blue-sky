package com.iblue.esky.auth.user.controller;

import com.alibaba.fastjson.JSON;
import com.iblue.esky.annotation.IgnoreResponseAdvice;
import com.iblue.esky.auth.user.entity.AuthUser;
import com.iblue.esky.auth.user.service.AuthUserService;
import com.iblue.esky.auth.user.service.IJWTService;
import com.iblue.esky.vo.JwtToken;
import com.iblue.esky.vo.UsernameAndPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <h1>对外暴露的授权服务接口</h1>
 * */
@Slf4j
@RestController
@RequestMapping("/authority")
public class AuthUserController {

    private final IJWTService ijwtService;

    public AuthUserController(IJWTService ijwtService) {
        this.ijwtService = ijwtService;
    }

    @Autowired
    private AuthUserService authUserService;
    /**
     * <h2>根据用户名获取用户信息</h2>
     * */
    @IgnoreResponseAdvice
    @GetMapping("/getUserInfo")
    public UsernameAndPassword getUserInfo(@RequestParam(value = "username") String username)
            throws Exception {
        log.info("request to get user with param: [{}]", username);
        AuthUser authUser = authUserService.getUserByUsername(username);
        UsernameAndPassword usernameAndPassword = new UsernameAndPassword();
        usernameAndPassword.setUsername(authUser.getUsername());
        usernameAndPassword.setPassword(authUser.getPassword());
        log.info("add user: [{}]",
                JSON.toJSONString(authUser));
        return usernameAndPassword;

    }
    /**
     * <h2>添加用户信息</h2>
     * */
    //@IgnoreResponseAdvice
    @PostMapping("/addUser")
    public Long addUser(@RequestBody UsernameAndPassword usernameAndPassword)
            throws Exception {
        log.info("request to add user with param: [{}]",
                JSON.toJSONString(usernameAndPassword));
        AuthUser authUser = new AuthUser();
        authUser.setUsername(usernameAndPassword.getUsername());
        String password = usernameAndPassword.getPassword();
        authUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        authUser.setExtraInfo("{}");
        Integer id =  authUserService.getBaseMapper().insert(authUser);
        log.info("add user: [{}]",
                JSON.toJSONString(authUser));
        return authUser.getId();

    }

    /**
     * <h2>从授权中心获取 Token (其实就是登录功能), 且返回信息中没有统一响应的包装</h2>
     * */
    @IgnoreResponseAdvice
    @PostMapping("/token")
    public JwtToken token(@RequestBody UsernameAndPassword usernameAndPassword)
            throws Exception {

        log.info("request to get token with param: [{}]",
                JSON.toJSONString(usernameAndPassword));
        return new JwtToken(ijwtService.generateToken(
                usernameAndPassword.getUsername(),
                usernameAndPassword.getPassword()
        ));
    }

    /**
     * <h2>注册用户并返回当前注册用户的 Token, 即通过授权中心创建用户</h2>
     * */
    @IgnoreResponseAdvice
    @PostMapping("/register")
    public JwtToken register(@RequestBody UsernameAndPassword usernameAndPassword)
            throws Exception {

        log.info("register user with param: [{}]", JSON.toJSONString(
                usernameAndPassword
        ));
        return new JwtToken(ijwtService.registerUserAndGenerateToken(
                usernameAndPassword
        ));
    }
}
