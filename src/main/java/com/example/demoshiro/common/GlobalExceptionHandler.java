package com.example.demoshiro.common;

import com.example.demoshiro.domain.good.Good;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public Map unauthorizedException(HttpServletRequest request, Exception e, HttpServletResponse response ) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code", 0);
        map.put("msg", "别逗了，你没权限操作这个功能");
        return map;
    }

}