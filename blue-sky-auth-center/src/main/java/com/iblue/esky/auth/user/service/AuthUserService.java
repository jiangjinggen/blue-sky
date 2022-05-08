package com.iblue.esky.auth.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iblue.esky.auth.user.entity.AuthUser;


public interface AuthUserService extends IService<AuthUser> {
    //通过用户名获取用户信息
    AuthUser getUserByUsername(String username);
    /**
     * <h2>根据用户名查询 EcommerceUser 对象</h2>
     * select * from t_ecommerce_user where username = ?
     * */
    AuthUser findByUsername(String username);

    /**
     * <h2>根据用户名和密码查询实体对象</h2>
     * select * from t_ecommerce_user where username = ? and password = ?
     * */
    AuthUser findByUsernameAndPassword(String username, String password);
}