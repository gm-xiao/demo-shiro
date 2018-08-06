package com.example.demoshiro.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @RequestMapping(value="/login/authc", method=RequestMethod.POST)
    public Map login(@RequestParam("username")String username, @RequestParam("password")String password){
        Map<String,Object> map = new HashMap<String,Object>();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            SecurityUtils.getSubject().login(token);
            map.put("code", 200);
            map.put("msg", "登录成功");
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            map.put("code", 500);
            map.put("msg", e.getMessage());
        } catch (AuthenticationException e) {
            map.put("code", 500);
            map.put("msg", "验证失败");
        }
        return map;
    }

}
