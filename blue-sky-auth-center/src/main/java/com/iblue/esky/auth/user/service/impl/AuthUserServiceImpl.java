package com.iblue.esky.auth.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iblue.esky.auth.user.entity.AuthUser;
import com.iblue.esky.auth.user.mapper.AuthUserMapper;
import com.iblue.esky.auth.user.service.AuthUserService;
import org.springframework.stereotype.Service;


@Service
public class AuthUserServiceImpl extends ServiceImpl<AuthUserMapper, AuthUser> implements AuthUserService {

    @Override
    public AuthUser getUserByUsername(String username) {
        QueryWrapper<AuthUser> query = new QueryWrapper<>();
        query.lambda().eq(AuthUser::getUsername,username);
        return this.baseMapper.selectOne(query);
    }

    @Override
    public AuthUser findByUsername(String username) {
        QueryWrapper<AuthUser> query = new QueryWrapper<>();
        query.lambda().eq(AuthUser::getUsername,username);
        return this.baseMapper.selectOne(query);
    }

    @Override
    public AuthUser findByUsernameAndPassword(String username, String password) {
        QueryWrapper<AuthUser> query = new QueryWrapper<>();
        query.lambda().eq(AuthUser::getUsername,username);
        query.lambda().eq(AuthUser::getPassword,password);
        return this.baseMapper.selectOne(query);
    }
}