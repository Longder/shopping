package com.longder.shopping.controller;

import com.longder.shopping.entity.po.SysRole;
import com.longder.shopping.entity.po.SysUser;
import com.longder.shopping.service.UserManageService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 主控制器，登陆，注册，首页等
 */
@Controller
public class MainController {

    @Resource
    private UserManageService userManageService;

    /**
     * 主页
     *
     * @return
     */
    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {
        //从session中获取当前登录的用户
        HttpSession session = request.getSession(true);
        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        if (ObjectUtils.isEmpty(securityContext)) {
            model.addAttribute("user", null);
        } else {
            model.addAttribute("user", securityContext.getAuthentication().getPrincipal());
        }

        return "index";
    }


    /**
     * 去登陆页
     *
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * 去注册页
     *
     * @return
     */
    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    /**
     * 注册用户
     *
     * @param sysUser
     * @return
     */
    @RequestMapping("/register")
    public String register(SysUser sysUser) {
        //注册一个买家
        userManageService.saveOneUser(sysUser, SysRole.ROLE_BUYER);
        return "redirect:toLogin";
    }

    /**
     * 检查登录名
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/checkLoginName")
    public Boolean checkLoginName(String loginName) {
        return userManageService.checkLoginName(loginName);
    }

    /**
     * 后台主页
     * @return
     */
    @GetMapping("/admin/index")
    public String adminIndex(){
        System.out.println("后台主页");
        return "adminIndex";
    }


    /**
     * 仪表盘，欢迎页面
     * @return
     */
    @GetMapping("/admin/dashboard")
    public String dashboard(){
        return "dashboard";
    }

}
