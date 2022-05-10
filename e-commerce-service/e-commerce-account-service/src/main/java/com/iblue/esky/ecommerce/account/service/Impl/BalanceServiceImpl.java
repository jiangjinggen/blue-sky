package com.iblue.esky.ecommerce.account.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iblue.esky.account.BalanceInfo;
import com.iblue.esky.ecommerce.account.entity.EcommerceBalance;
import com.iblue.esky.ecommerce.account.mapper.EcommerceBalanceMapper;
import com.iblue.esky.ecommerce.account.service.IBalanceService;
import com.iblue.esky.filter.AccessContext;
import com.iblue.esky.vo.LoginUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BalanceServiceImpl extends ServiceImpl<EcommerceBalanceMapper, EcommerceBalance> implements IBalanceService {
    /** 根据 userId 查询 EcommerceBalance 对象 */
    public EcommerceBalance findByUserId(Long userId){
        QueryWrapper<EcommerceBalance> query = new QueryWrapper<>();
        query.lambda().eq(EcommerceBalance::getUserId, userId);
        return this.baseMapper.selectOne(query);

    }
    @Override
    public BalanceInfo getCurrentUserBalanceInfo() {

        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();
        BalanceInfo balanceInfo = new BalanceInfo(
                loginUserInfo.getId(), 0L
        );

        EcommerceBalance ecommerceBalance =
                findByUserId(loginUserInfo.getId());
        if (null != ecommerceBalance) {
            balanceInfo.setBalance(ecommerceBalance.getBalance());
        } else {
            // 如果还没有用户余额记录, 这里创建出来，余额设定为0即可
            EcommerceBalance newBalance = new EcommerceBalance();
            newBalance.setUserId(loginUserInfo.getId());
            newBalance.setBalance(0L);
            Integer id = this.baseMapper.insert(newBalance);
            log.info("init user balance record: [{}]",
                    id);
        }

        return balanceInfo;
    }

    @Override
    public BalanceInfo deductBalance(BalanceInfo balanceInfo) {

        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();

        // 扣减用户余额的一个基本原则: 扣减额 <= 当前用户余额
        EcommerceBalance ecommerceBalance =
                findByUserId(loginUserInfo.getId());
        if (null == ecommerceBalance
                || ecommerceBalance.getBalance() - balanceInfo.getBalance() < 0
        ) {
            throw new RuntimeException("user balance is not enough!");
        }

        Long sourceBalance = ecommerceBalance.getBalance();
        ecommerceBalance.setBalance(ecommerceBalance.getBalance() - balanceInfo.getBalance());
        Integer id = this.baseMapper.insert(ecommerceBalance);
        log.info("deduct balance: [{}], [{}], [{}]",
                id, sourceBalance,
                balanceInfo.getBalance());

        return new BalanceInfo(
                ecommerceBalance.getUserId(),
                ecommerceBalance.getBalance()
        );
    }
}
