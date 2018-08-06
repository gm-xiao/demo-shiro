package com.example.demoshiro.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping("/login/authc")
    public String login(@RequestParam("username")String username, @RequestParam("password")String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {

            SecurityUtils.getSubject().login(token);

        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return "error";
        } catch (AuthenticationException e) {
            return "error";
        }
        return "index";
    }

}
