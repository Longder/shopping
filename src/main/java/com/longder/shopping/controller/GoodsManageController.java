package com.longder.shopping.controller;

import com.longder.shopping.entity.enumeration.GoodsType;
import com.longder.shopping.entity.po.Goods;
import com.longder.shopping.entity.po.SysUser;
import com.longder.shopping.security.SecurityUtil;
import com.longder.shopping.service.GoodsManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 商品管理控制器
 */
@Controller
@RequestMapping("/admin/goods")
public class GoodsManageController {

    @Resource
    private GoodsManageService goodsManageService;

    /**
     * 商品列表，卖家只能查到自己的商品
     * @return
     */
    @GetMapping("/list")
    public String list(Model model){
        SysUser currentUser = SecurityUtil.getCurrentUser();
        model.addAttribute("goodsList",goodsManageService.listGoods(currentUser));
        return "goods/list";
    }

    /**
     * 去添加页面
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd(Model model){
        //商品类型
        model.addAttribute("goodsTypes", GoodsType.values());
        return "goods/add-goods-modal";
    }

    /**
     * 添加商品
     * @return
     */
    @PostMapping("/add")
    public String add(Goods goods){
        SysUser currentUser = SecurityUtil.getCurrentUser();
        goodsManageService.saveOneGoods(goods,currentUser);
        return "redirect:list";
    }

    /**
     * 去修改商品
     * @return
     */
    @GetMapping("/toUpdate")
    public String toUpdate(Long goodsId,Model model){
        //商品类型
        model.addAttribute("goodsTypes", GoodsType.values());
        model.addAttribute("goods",goodsManageService.getOneGoods(goodsId));
        return "goods/update-goods-modal";
    }

    /**
     * 修改商品
     * @param goods
     * @return
     */
    @PostMapping("/update")
    public String update(Goods goods){
        SysUser currentUser = SecurityUtil.getCurrentUser();
        goodsManageService.saveOneGoods(goods,currentUser);
        return "redirect:list";
    }

    /**
     * 去修改数量页面
     * @param goodsId
     * @param model
     * @return
     */
    @GetMapping("/toUpdateCount")
    public String toUpdateCount(Long goodsId,Model model){
        model.addAttribute("goods",goodsManageService.getOneGoods(goodsId));
        return "goods/update-goods-count-modal";
    }

    /**
     * 修改库存数量
     * @param goods
     * @return
     */
    @PostMapping("/updateCount")
    public String updateCount(Goods goods){
        goodsManageService.updateGoodsCount(goods);
        return "redirect:/admin/goods/list";
    }
}
