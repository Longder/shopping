package com.longder.shopping.controller;

import com.longder.shopping.entity.po.ShoppingCartDetail;
import com.longder.shopping.entity.po.SysUser;
import com.longder.shopping.security.SecurityUtil;

import com.longder.shopping.service.OrderManageService;
import com.longder.shopping.service.ShoppingCartManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


/**
 * 订单管理的控制器
 */
@Controller
@RequestMapping("/order")
@Slf4j
public class OrderManageController {
    @Resource
    private ShoppingCartManageService shoppingCartManageService;
    @Resource
    private OrderManageService orderManageService;
    /**
     * 初始化订单
     * 根据当前用户的购物车信息，生成订单
     * @return
     */
    @GetMapping("/init")
    public String initOrder(Model model){
        SysUser sysUser = SecurityUtil.getCurrentUser();
        model.addAttribute("user",sysUser);

        List<ShoppingCartDetail> cartDetailList = shoppingCartManageService.listAll(sysUser);
        //计算总价
        AtomicReference<Double> total = new AtomicReference<>(0D);
        cartDetailList.forEach(cartDetail-> total.updateAndGet(v -> v + cartDetail.getPrice()));
        model.addAttribute("cartList",cartDetailList);
        model.addAttribute("total",total.get());
        return "order";
    }

    /**
     * 提交订单
     * @return
     */
    @PostMapping("/submit")
    public String submitOrder(){
        SysUser sysUser = SecurityUtil.getCurrentUser();
        orderManageService.submitOrder(sysUser);
        return "redirect:/";
    }

    /**
     * 订单列表
     * @return
     */
    @GetMapping("/list")
    public String list(Model model){
        SysUser sysUser = SecurityUtil.getCurrentUser();
        model.addAttribute("orderList",orderManageService.listOrder(sysUser));
        return "order/list";
    }

    /**
     * 发货
     * @param orderId
     * @return
     */
    @GetMapping("/deliver")
    public String deliver(Long orderId){
        orderManageService.deliver(orderId);
        return "redirect:/order/list";
    }
}
