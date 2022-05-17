package com.iblue.esky.ecommerce.goods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iblue.esky.common.TableId;
import com.iblue.esky.ecommerce.goods.constant.BrandCategory;
import com.iblue.esky.ecommerce.goods.constant.GoodsCategory;
import com.iblue.esky.ecommerce.goods.entity.EcommerceGoods;
import com.iblue.esky.ecommerce.goods.mapper.EcommerceGoodsMapper;
import com.iblue.esky.ecommerce.goods.vo.PageSimpleGoodsInfo;
import com.iblue.esky.goods.DeductGoodsInventory;
import com.iblue.esky.goods.GoodsInfo;
import com.iblue.esky.goods.SimpleGoodsInfo;

import java.util.List;
import java.util.Optional;

/**
 * <h1>商品微服务相关服务接口定义</h1>
 * */
public interface IGoodsService extends IService<EcommerceGoods> {

    /**
     * <h2>根据查询条件查询商品表, 并限制返回结果</h2>
     * select * from t_ecommerce_goods where goods_category = ? and brand_category = ?
     * and goods_name = ? limit 1;
     * */
    Optional<EcommerceGoods> findFirst1ByGoodsCategoryAndBrandCategoryAndGoodsName(
            GoodsCategory goodsCategory, BrandCategory brandCategory,
            String goodsName
    );
    //获取用户列表
    IPage<EcommerceGoods> getAllGoodsList(Long currentPage, Long pageSize);
    /**
     * <h2>根据 TableId 查询商品详细信息</h2>
     * */
    List<GoodsInfo> getGoodsInfoByTableId(TableId tableId);

    /**
     * <h2>获取分页的商品信息</h2>
     * */
    PageSimpleGoodsInfo getSimpleGoodsInfoByPage(Long page);

    /**
     * <h2>根据 TableId 查询简单商品信息</h2>
     * */
    List<SimpleGoodsInfo> getSimpleGoodsInfoByTableId(TableId tableId);

    /**
     * <h2>扣减商品库存</h2>
     * */
    Boolean deductGoodsInventory(List<DeductGoodsInventory> deductGoodsInventories);
}