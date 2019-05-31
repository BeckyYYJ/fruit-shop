package com.bb.controller;

import com.bb.entity.User;
import com.bb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * created by becky_yyj
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/user/toLogin.action")
    public String toLogin(){
        return "/login.jsp";
    }
    @RequestMapping("/user/login.action")
    public String login(User user, Model model, HttpServletRequest request){
        Map<String,String> map=new HashMap<String,String>();
        map.put("username",user.getUserName());
        map.put("password",user.getPassword());
        List<User> userList=userService.find(map);
        if(userList!=null&&userList.size()>0){
            request.getSession().setAttribute("user",userList.get(0));
            // request.getSession().setAttribute("user",user);注意一个只有密码，一个信息更多
            return "/home.jsp";
        }
        return "/login.jsp";
    }
    @RequestMapping("/user/registerPage.action")
    public String toRegister(){
        return "/register.jsp";
    }
    @RequestMapping("/user/register.action")
    public String register(User user, Model model, HttpServletRequest request,
                           HttpServletResponse response)throws Exception{
        Map<String,String> map=new HashMap<String,String>();
        map.put("username",user.getUserName());
        List<User> list=userService.find(map);
        if(list!=null&&list.size()>0) {
            model.addAttribute("errorMsg", "注册失败，该用户名已经被注册");
            return "/register.jsp";
        }
        user.setUserId(UUID.randomUUID().toString());
        userService.insert(user);
        return "/login.jsp";

    }
}
