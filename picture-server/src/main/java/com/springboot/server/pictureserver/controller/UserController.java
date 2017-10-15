package com.springboot.server.pictureserver.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.server.pictureserver.model.User;
import com.springboot.server.pictureserver.service.IUserService;


@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/query")
	@ResponseBody
	@RequiresPermissions("USER_QUERY")
	public List<User> query() {
		List<User> userIngfo;
		try {
//			User user = new User();
//			user.setRealName("MoTing");
			List<User> userList = userService.query(null);
			userIngfo = userList;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return userIngfo;
	}
	
	@RequestMapping(value="/add")
	@ResponseBody
	public int add() {
		int a = -1;
		User user=new User();
		user.setUsername("moting");
//		user.setPassword("123");
		user.setRealName("MoTing3");
		try {
			a = userService.add(user);
		}catch(Exception ex) {
			ex.printStackTrace();
			return a;
		}
		return a;
	}
	
	@RequestMapping(value="/del")
	@ResponseBody
	public int delete() {
		int a;
		try {
			a = userService.delete(9,10);
		}catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		return a;
	}
	
	@RequestMapping(value="/upadte")
	@ResponseBody
	public int upadte() {
		int a;
		User user=new User();
		user.setUsername("moting");
		user.setPassword("123");
		user.setRealName("MoTing");
		try {
			a = userService.add(user);
		}catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		return a;
	}
	
	@RequestMapping("/login")
    public String login() {
        return "login";
    }
	
    @RequestMapping("/loginUser")
    public String loginUser(String username,String password,HttpSession session) {
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
