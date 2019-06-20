package com.longder.shopping.service;

import com.longder.shopping.entity.po.Goods;
import com.longder.shopping.entity.po.SysUser;

import java.util.List;

/**
 * 商品管理的业务层
 */
public interface GoodsManageService {

    /**
     * 根据用户查询商品列表
     * @param sysUser
     * @return
     */
    List<Goods> listGoods(SysUser sysUser);

    /**
     * 查询所有商品
     * @return
     */
    List<Goods> listAllGoods();

    /**
     * 保存一个商品（新增修改都走）
     * @param goods
     * @param seller
     */
    void saveOneGoods(Goods goods,SysUser seller);

    /**
     * 查询获取一个商品
     * @param goodsId
     * @return
     */
    Goods getOneGoods(Long goodsId);

    /**
     * 修改商品库存
     * @param goods
     */
    void updateGoodsCount(Goods goods);
}
