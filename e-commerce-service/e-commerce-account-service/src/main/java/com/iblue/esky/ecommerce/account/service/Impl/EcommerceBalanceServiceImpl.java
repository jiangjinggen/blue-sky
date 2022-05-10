package com.iblue.esky.ecommerce.account.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iblue.esky.ecommerce.account.entity.EcommerceBalance;
import com.iblue.esky.ecommerce.account.mapper.EcommerceBalanceMapper;
import com.iblue.esky.ecommerce.account.service.EcommerceBalanceService;
import org.springframework.stereotype.Service;

@Service
public class EcommerceBalanceServiceImpl extends ServiceImpl<EcommerceBalanceMapper, EcommerceBalance> implements EcommerceBalanceService {
    /** 根据 userId 查询 EcommerceBalance 对象 */
    public EcommerceBalance findByUserId(Long userId){
        QueryWrapper<EcommerceBalance> query = new QueryWrapper<>();
        query.lambda().eq(EcommerceBalance::getUserId, userId);
        return this.baseMapper.selectOne(query);

    }
}
