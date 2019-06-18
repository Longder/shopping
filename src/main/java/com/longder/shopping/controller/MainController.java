package com.longder.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主控制器，登陆，注册，首页等
 */
@Controller
public class MainController {

    /**
     * 主页
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    /**
     * 去登陆页
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }



}
