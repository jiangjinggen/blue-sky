package com.iblue.esky.ecommerce.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iblue.esky.account.BalanceInfo;
import com.iblue.esky.ecommerce.account.entity.EcommerceBalance;

public interface IBalanceService extends IService<EcommerceBalance> {

    /** 根据 userId 查询 EcommerceBalance 对象 */
    EcommerceBalance findByUserId(Long userId);

    /**
     * <h2>获取当前用户余额信息</h2>
     * */
    BalanceInfo getCurrentUserBalanceInfo();

    /**
     * <h2>扣减用户余额</h2>
     * @param balanceInfo 代表想要扣减的余额
     * */
    BalanceInfo deductBalance(BalanceInfo balanceInfo);
}
