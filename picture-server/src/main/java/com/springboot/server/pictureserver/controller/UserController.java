package com.springboot.server.pictureserver.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.server.pictureserver.model.User;
import com.springboot.server.pictureserver.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/query")
	public List<User> query(User user){
		List<User> userList = userService.query(null);
		return userList;
	}
	
	@RequestMapping("/login")
    public String login() {
        return "login";
    }
	
    @RequestMapping("/loginUser")
    public String loginUser(String username,String password,HttpSession session) {
        System.out.println("开始登陆认证");
    	UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
        	//完成登录
        	subject.login(usernamePasswordToken);   
            User user=(User) subject.getPrincipal();
            session.setAttribute("user", user);
            return "index";
        } catch(Exception e) {
        	e.printStackTrace();
            return "login";//返回登录页面
        }
        
    }
    @RequestMapping("/logOut")
    public String logOut(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        session.removeAttribute("user");
        return "login";
    }
}
