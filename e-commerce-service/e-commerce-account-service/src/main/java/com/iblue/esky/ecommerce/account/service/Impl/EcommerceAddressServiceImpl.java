package com.iblue.esky.ecommerce.account.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iblue.esky.ecommerce.account.entity.EcommerceAddress;
import com.iblue.esky.ecommerce.account.mapper.EcommerceAddressMapper;
import com.iblue.esky.ecommerce.account.service.EcommerceAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcommerceAddressServiceImpl extends ServiceImpl<EcommerceAddressMapper, EcommerceAddress> implements EcommerceAddressService {

    /**
     * <h2>根据 用户 id 查询地址信息</h2>
     * */
    @Override
    public List<EcommerceAddress> findAllByUserId(Long userId)
    {
        QueryWrapper<EcommerceAddress> query = new QueryWrapper<>();
        query.lambda().eq(EcommerceAddress::getUserId, userId);
        return this.baseMapper.selectList(query);
    }
}