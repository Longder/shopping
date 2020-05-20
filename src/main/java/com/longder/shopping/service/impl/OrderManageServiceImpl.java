package com.longder.shopping.service.impl;

import com.longder.shopping.entity.po.Goods;
import com.longder.shopping.entity.po.OrderDetail;
import com.longder.shopping.entity.po.ShoppingCartDetail;
import com.longder.shopping.entity.po.SysUser;
import com.longder.shopping.repository.GoodsRepository;
import com.longder.shopping.repository.OrderDetailRepository;
import com.longder.shopping.repository.ShoppingCartDetailRepository;
import com.longder.shopping.service.OrderManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单管理业务层
 */
@Service
public class OrderManageServiceImpl implements OrderManageService {

    @Resource
    private ShoppingCartDetailRepository shoppingCartDetailRepository;
    @Resource
    private OrderDetailRepository orderDetailRepository;
    @Resource
    private GoodsRepository goodsRepository;

    /**
     * 提交订单
     *
     * @param buyer
     */
    @Override
    @Transactional
    public void submitOrder(SysUser buyer) {
        //查询出买家购物车中的所有内容
        List<ShoppingCartDetail> detailList = shoppingCartDetailRepository.listByBuyer(buyer);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        detailList.forEach(detail -> {
            //一个商品生成一个订单详情
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setBuyer(buyer);
            Goods goods = detail.getGoods();
            orderDetail.setGoods(goods);
            orderDetail.setSeller(detail.getGoods().getSeller());
            orderDetail.setCount(detail.getCount());
            orderDetail.setTotal(detail.getPrice());
            //默认未发货
            orderDetail.setDeliver(false);
            orderDetailList.add(orderDetail);
            //商品数量减少
            goods.setCount(goods.getCount() - detail.getCount());
            goodsRepository.save(goods);
        });
        orderDetailRepository.saveAll(orderDetailList);
        //清空用户的购物车
        shoppingCartDetailRepository.deleteAll(detailList);
    }

    /**
     * 订单列表
     *
     * @param seller
     * @return
     */
    @Override
    public List<OrderDetail> listOrder(SysUser seller) {
        return orderDetailRepository.listBySeller(seller);
    }

    /**
     * 发货某个订单
     *
     * @param orderId
     */
    @Override
    @Transactional
    public void deliver(Long orderId) {
        OrderDetail orderDetail = orderDetailRepository.getOne(orderId);
        orderDetail.setDeliver(true);
        orderDetailRepository.save(orderDetail);
    }
}
