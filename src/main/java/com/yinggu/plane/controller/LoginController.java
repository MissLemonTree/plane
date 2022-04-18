package com.yinggu.plane.controller;

import com.yinggu.plane.common.Result;
import com.yinggu.plane.common.SmsUtils;
import com.yinggu.plane.pojo.User;
import com.yinggu.plane.pojo.vo.LoginVo;
import com.yinggu.plane.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/flight")
public class LoginController {

    private static String getCode=null;

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/user/login")
    @ResponseBody
    public Result userLogin(LoginVo loginVo, HttpSession session){
        User user = userService.selectUser(loginVo.getNickname());
        if(user!=null){
            if(user.getPassword().equals(loginVo.getPassword())){
                user.setPassword(null);
                session.setAttribute("user",user);
                return Result.success("登录成功");
            }else {
                return Result.fail("用户名或密码错误");
            }
        }else {
            return Result.fail("登陆失败，您还没有注册");
        }

    }

    @RequestMapping("/getUser")
    @ResponseBody
    public Result getUser(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user != null){
            return Result.success(user.getNickname());
        }else {
            return Result.success("请登录");
        }
    }

    @RequestMapping("/phone/index")
    public String index(){
        return "index";
    }


    @RequestMapping("/phone/login")
    public String toPhoneLogin(){
        return "login-phone";
    }

    @RequestMapping("/phoneLogin")
    @ResponseBody
    public Result phoneLogin(String phoneNum){
        User user = userService.selectByPhoneNum(phoneNum);
        if(user==null){
            return Result.fail("您还未注册，请先注册");
        }else {
            getCode = SmsUtils.getCode();
            SmsUtils.sendMessage(phoneNum, getCode);
            return Result.success("请求成功");
        }
    }


    @RequestMapping("/phone/check")
    @ResponseBody
    public Result check(String code){
        if(getCode!=null && getCode!=""){
            if(code.equals(getCode)){
                return Result.success("1");
            }
            else {
                return Result.fail("验证码错误");
            }
        }else {
            return Result.fail("验证码不能为空");
        }
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/flight/index";
    }


}
