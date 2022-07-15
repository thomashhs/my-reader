package com.imooc.reader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
    @GetMapping("/register.html")
    public ModelAndView showRegister(){
        return new ModelAndView("/register");
    }
}
