package com.imooc.reader.controller;

import com.imooc.reader.entity.Evaluation;
import com.imooc.reader.entity.Member;
import com.imooc.reader.service.MemberService;
import com.imooc.reader.service.exception.BussinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MemberController {
    @Resource
    private MemberService memberService;

    @GetMapping("/register.html")
    public ModelAndView showRegister(){
        return new ModelAndView("/register");
    }

    //vc:前台输入验证码  username:用户名 password:密码 nickname:昵称
    @PostMapping("/registe")
    @ResponseBody
    public Map registe(String vc, String username, String password, String nickname, HttpServletRequest request){
        Map result=new HashMap();
        String verifyCode=(String)request.getSession().getAttribute("kaptchaVerifyCode");
        System.out.println("verifyCode:"+verifyCode);
        if(vc==null || verifyCode==null || !verifyCode.equalsIgnoreCase(vc)){
            result.put("code","VC001");
            result.put("msg","验证码错误");
        }else{
            try{
                memberService.createMember(username,password,nickname);
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

    @GetMapping("/login.html")
    public ModelAndView showLogin(){
        return new ModelAndView("/login");
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
                Member member=memberService.checkLogin(username,password);
                session.setAttribute("loginMember",member);
                System.out.println("member:"+member.getNickname());
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

    @PostMapping("/update_read_state")
    @ResponseBody
    public Map updateReadState(Long memberId, Long bookId, Integer readState){
        Map result=new HashMap();
        try {
            memberService.updateMemberReadState(memberId,bookId,readState);
            result.put("code","0");
            result.put("msg","success");
        }catch (BussinessException ex){
            ex.printStackTrace();
            result.put("code",ex.getCode());
            result.put("msg",ex.getMsg());
        }

        return result;
    }

    @PostMapping("/evaluate")
    @ResponseBody
    public Map evaluate(Long memberId,Long bookId,Integer score,String content){
        Map result=new HashMap();
        try {
            memberService.evaluate(memberId,bookId,score,content);
            result.put("code","0");
            result.put("msg","success");
        }catch (BussinessException ex){
            ex.printStackTrace();
            result.put("code",ex.getCode());
            result.put("msg",ex.getMsg());
        }

        return result;
    }

    @PostMapping("/enjoy")
    @ResponseBody
    public Map enjoy(Long evaluationId){
        Map result=new HashMap();
        try {
            Evaluation evaluation=memberService.enjoy(evaluationId);
            result.put("code","0");
            result.put("msg","success");
            result.put("evaluation",evaluation);
        }catch (BussinessException ex){
            ex.printStackTrace();
            result.put("code",ex.getCode());
            result.put("msg",ex.getMsg());
        }

        return result;
    }
}
