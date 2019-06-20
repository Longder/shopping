package com.longder.shopping.controller;

import com.longder.shopping.entity.po.ShoppingCartDetail;
import com.longder.shopping.entity.po.SysUser;
import com.longder.shopping.security.SecurityUtil;
import com.longder.shopping.service.ShoppingCartManageService;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车相关功能控制器
 */
@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Resource
    private ShoppingCartManageService shoppingCartManageService;

    /**
     * 购物车列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public List<ShoppingCartDetail> listCartDetail() {
        System.out.println("购物车列表！");
        SysUser currentUser = SecurityUtil.getCurrentUser();
        if (ObjectUtils.isEmpty(currentUser)) {//没登录
            return null;
        } else {
            return shoppingCartManageService.listAll(currentUser);
        }
    }

    /**
     * 添加到购物车
     *
     * @param goodsId
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public String add(Long goodsId) {
        System.out.println("添加到购物车：");
        System.out.println(goodsId);
        SysUser currentUser = SecurityUtil.getCurrentUser();
        shoppingCartManageService.addToCart(goodsId, currentUser);
        return "ok";
    }

    /**
     * 增加数量
     * @return
     */
    @ResponseBody
    @RequestMapping("/addCount")
    public String addCount(Long detailId){
        shoppingCartManageService.addCount(detailId);
        return "ok";
    }

    /**
     * 减少数量
     * @return
     */
    @ResponseBody
    @RequestMapping("/reduceCount")
    public String reduceCount(Long detailId){
        shoppingCartManageService.reduceCount(detailId);
        return "ok";
    }

    /**
     * 移除购物车中的某一种商品
     * @return
     */
    @ResponseBody
    @RequestMapping("/remove")
    public String remove(Long detailId){
        shoppingCartManageService.removeDetail(detailId);
        return "ok";
    }
}
