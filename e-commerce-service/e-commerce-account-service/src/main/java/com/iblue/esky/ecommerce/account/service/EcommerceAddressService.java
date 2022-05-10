package com.iblue.esky.ecommerce.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iblue.esky.ecommerce.account.entity.EcommerceAddress;

import java.util.List;

public interface EcommerceAddressService extends IService<EcommerceAddress> {
    /**
     * <h2>根据 用户 id 查询地址信息</h2>
     * */
    List<EcommerceAddress> findAllByUserId(Long userId);

}