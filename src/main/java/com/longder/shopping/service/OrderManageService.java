package com.longder.shopping.service;

import com.longder.shopping.entity.po.SysUser;

/**
 * 订单管理的业务层
 */
public interface OrderManageService {

    /**
     * 提交订单
     * @param sysUser
     */
    void submitOrder(SysUser sysUser);
}
