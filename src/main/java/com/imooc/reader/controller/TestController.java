package com.imooc.reader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {
    @GetMapping("/test/t1")
    public ModelAndView test1(){
        ModelAndView mav=new ModelAndView("test");
        return mav;
    }

    @GetMapping("/test/t2")
    @ResponseBody
    public Map test2(){
        Map map=new HashMap();
        map.put("result","OK");
        return map;
    }

}
