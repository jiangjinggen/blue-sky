package com.iblue.esky.ecommerce.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iblue.esky.account.AddressInfo;
import com.iblue.esky.common.TableId;
import com.iblue.esky.ecommerce.account.entity.EcommerceAddress;

import java.util.List;

public interface IAddressService extends IService<EcommerceAddress> {
    /**
     * <h2>根据 用户 id 查询地址信息</h2>
     * */
    List<EcommerceAddress> findAllByUserId(Long userId);
    /**
     * <h2>创建用户地址信息</h2>
     * */
    TableId createAddressInfo(AddressInfo addressInfo);

    /**
     * <h2>获取当前登录的用户地址信息</h2>
     * */
    AddressInfo getCurrentAddressInfo();

    /**
     * <h2>通过 id 获取用户地址信息, id 是 EcommerceAddress 表的主键</h2>
     * */
    AddressInfo getAddressInfoById(Long id);

    /**
     * <h2>通过 TableId 获取用户地址信息</h2>
     * */
    AddressInfo getAddressInfoByTableId(TableId tableId);

}