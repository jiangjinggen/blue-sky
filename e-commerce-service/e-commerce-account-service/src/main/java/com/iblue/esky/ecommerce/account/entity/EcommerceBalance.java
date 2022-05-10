package com.iblue.esky.ecommerce.account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <h1>用户账户余额表实体类定义</h1>
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_ecommerce_balance")
public class EcommerceBalance {

    /** 自增主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 用户 id */
    private Long userId;

    /** 账户余额 */
    private Long balance;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;
}
