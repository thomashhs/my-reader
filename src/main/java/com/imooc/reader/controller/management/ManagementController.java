package com.imooc.reader.controller.management;

import com.imooc.reader.entity.Member;
import com.imooc.reader.entity.User;
import com.imooc.reader.service.UserService;
import com.imooc.reader.service.exception.BussinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/management")
public class ManagementController {

    @Resource
    private UserService userService;

    @GetMapping("/index")
    public ModelAndView showIndex(HttpSession session){
        User user=(User)session.getAttribute("loginAdmin");
        if(user==null){
            return new ModelAndView("/management/login");
        }
        return new ModelAndView("/management/index");
    }

    @GetMapping("/login.html")
    public ModelAndView showLogin(){
        return new ModelAndView("/management/login");
    }

    @PostMapping("/check_login")
    @ResponseBody
    public Map login(String vc, String username, String password, HttpSession session){

        Map result=new HashMap();
        String verifyCode=(String)session.getAttribute("kaptchaVerifyCode");
        System.out.println("verifyCode:"+verifyCode);
        if(vc==null || verifyCode==null || !verifyCode.equalsIgnoreCase(vc)){
            result.put("code","VC001");
            result.put("msg","验证码错误");
        }else{
            try{
                User user=userService.checkLogin(username,password);
                session.setAttribute("loginAdmin",user);
                result.put("code","0");
                result.put("msg","success");
            }catch (BussinessException ex){
                ex.printStackTrace();
                result.put("code",ex.getCode());
                result.put("msg",ex.getMsg());
            }

        }
        return result;
    }

    @GetMapping("/logout")
    public ModelAndView showLogout(HttpSession session){
        session.invalidate();
        return new ModelAndView("/management/login");
    }
}
