package com.example.demoshiro.web;

import com.example.demoshiro.domain.good.Good;
import com.example.demoshiro.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GoodController {

    @Autowired
    private GoodService goodService;

    /**
     * 分页高级查询
     * @param page
     * @param limit
     * @param good
     * @return
     */
    @RequestMapping(value="/good/list", method=RequestMethod.GET)
    public Map goods(@RequestParam("page")Integer page, @RequestParam("limit")Integer limit,Good good){
        Map<String,Object> map = new HashMap<String,Object>();
        Page<Good> goods = goodService.findAllPage(page, limit, good);
        map.put("code", 0);
        map.put("data", goods.getContent());
        map.put("count", goods.getTotalElements());
        return map;
    }

    /**
     * 不分页高级查询
     * @param good
     * @return
     */
    @RequestMapping(value="/good/lists", method=RequestMethod.GET)
    public Map goods(Good good){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Good> goods = goodService.findAll(good);
        map.put("code", 0);
        map.put("data", goods);
        return map;
    }

}
