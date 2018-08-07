package com.example.demoshiro.web;

import com.example.demoshiro.domain.good.Good;
import com.example.demoshiro.service.GoodService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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

    @RequiresPermissions("good:save")
    @RequestMapping(value="/good/save", method=RequestMethod.POST)
    public Map save(Good good){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            if (StringUtils.isBlank(good.getId())){
                good.setState("0");
                good.setCreateTime(new Date());
                goodService.save(good);
            }else{
                goodService.update(good);
            }
            map.put("code", 200);
            map.put("msg", "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 500);
            map.put("msg", "保存失败");
        }
        return map;
    }

    @RequiresPermissions("good:del")
    @RequestMapping(value="/good/del", method=RequestMethod.POST)
    public Map del(@RequestParam("id")String id){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            goodService.delete(id);
            map.put("code", 200);
            map.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 500);
            map.put("msg", "删除失败");
        }
        return map;
    }


}
