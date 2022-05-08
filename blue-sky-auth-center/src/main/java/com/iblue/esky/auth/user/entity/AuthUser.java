package com.iblue.esky.auth.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <h1>用户表实体类定义</h1>
 * */
@Data
@TableName("t_auth_user")
public class AuthUser implements Serializable {

    /** 自增主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 用户名 */
    private String username;

    /** MD5 密码 */
    private String password;

    /** 额外的信息, json 字符串存储 */
    private String extraInfo;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;
}
