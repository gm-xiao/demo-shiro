package com.example.demoshiro.web;


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

}