package com.iblue.esky.ecommerce.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iblue.esky.ecommerce.account.entity.EcommerceBalance;

public interface EcommerceBalanceService extends IService<EcommerceBalance> {

    /** 根据 userId 查询 EcommerceBalance 对象 */
    EcommerceBalance findByUserId(Long userId);
}
