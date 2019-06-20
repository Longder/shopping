package com.longder.shopping.service;

import com.longder.shopping.entity.po.ShoppingCartDetail;
import com.longder.shopping.entity.po.SysUser;

import java.util.List;

/**
 * 购物车管理的业务
 */
public interface ShoppingCartManageService {

    /**
     * 查询当前用户的购物车里的内容
     * @param sysUser
     * @return
     */
    List<ShoppingCartDetail> listAll(SysUser sysUser);

    /**
     * 添加到购物车
     * @param goodsId
     */
    void addToCart(Long goodsId,SysUser sysUser);

    /**
     * 购物车中某个商品数量加一
     */
    void addCount(Long detailId);

    /**
     * 购物车中某个商品数量减一
     */
    void reduceCount(Long detailId);

    /**
     * 从购物车中移除某个商品
     * @param detailId
     */
    void removeDetail(Long detailId);
}
