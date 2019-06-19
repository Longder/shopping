package com.longder.shopping.controller;

import com.longder.shopping.service.UserManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
     * 去修改用户
     * @return
     */
    @GetMapping("/toUpdate")
    public String toUpdate(){
        return "user/update-user-modal";
    }

}
