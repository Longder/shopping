package com.longder.shopping.service;

import com.longder.shopping.entity.po.OrderDetail;
import com.longder.shopping.entity.po.SysUser;

import java.util.List;

/**
 * 订单管理的业务层
 */
public interface OrderManageService {

    /**
     * 提交订单
     * @param buyer
     */
    void submitOrder(SysUser buyer);

    /**
     * 订单列表
     * @param seller
     * @return
     */
    List<OrderDetail> listOrder(SysUser seller);

    /**
     * 发货某个订单
     * @param orderId
     */
    void deliver(Long orderId);
}
