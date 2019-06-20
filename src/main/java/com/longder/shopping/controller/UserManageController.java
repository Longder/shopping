package com.longder.shopping.controller;

import com.longder.shopping.entity.po.SysUser;
import com.longder.shopping.service.UserManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 用户管理的控制器
 */
@Controller
@RequestMapping("/admin/user")
public class UserManageController {

    @Resource
    private UserManageService userManageService;

    /**
     * 用户列表
     *
     * @return
     */
    @GetMapping("/list")
    public String listUser(Model model) {
        model.addAttribute("userList", userManageService.listUsers());
        return "user/list";
    }

    /**
     * 去添加用户
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd(){
        return "user/add-user-modal";
    }

    /**
     * 添加用户
     * @return
     */
    @PostMapping("/add")
    public String add(SysUser sysUser){
        userManageService.saveOneUser(sysUser,sysUser.getRole());
        return "redirect:list";
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @GetMapping("/delete")
    public String delete(Long userId){
        userManageService.deleteOneUser(userId);
        return "redirect:list";
    }

    /**
     * 去修改用户
     * @return
     */
    @GetMapping("/toUpdate")
    public String toUpdate(Long userId,Model model){
        model.addAttribute("user",userManageService.getOneUser(userId));
        return "user/update-user-modal";
    }

}
