package com.example.demoshiro.web;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String main(){
        return "/index";
    }

    @RequestMapping("/index")
    public String index(){
        return "/index";
    }

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

    @RequestMapping("/goods")
    public String goods(){
        return "/good/goods";
    }

    @RequiresPermissions("good:edit")
    @RequestMapping("/good/edit")
    public String editGood(){
        return "/good/edit";
    }


}
