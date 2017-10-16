package com.springboot.server.pictureserver.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.server.pictureserver.model.User;
import com.springboot.server.pictureserver.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/query")
	@ResponseBody
	@RequiresPermissions("USER_QUERY")
	public String query(User user){
		try {
			List<User> userList = userService.query(null);
			return userList.toString();
		} catch (AuthorizationException e) {
			return "refuse";
		} catch (Exception e) {
			return "refuse";
		}
		
	}
	
	@RequestMapping("/add")
	@ResponseBody
	@RequiresPermissions("USER_ADD")
	public String add(User user){
		try {
			List<User> userList = userService.query(null);
			return userList.toString();
		} catch (AuthorizationException e) {
			return "refuse";
		} catch (Exception e) {
			return "refuse";
		}
	}
	
	@RequestMapping("/update")
	@ResponseBody
	@RequiresPermissions("USER_UPDATE")
	public String update(User user){
		try {
			List<User> userList = userService.query(null);
			return userList.toString();
		} catch (AuthorizationException e) {
			return "refuse";
		} catch (Exception e) {
			return "refuse";
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	@RequiresPermissions("USER_DELETE")
	public String delete(User user){
		try {
			List<User> userList = userService.query(null);
			return userList.toString();
		} catch (AuthorizationException e) {
			return "refuse";
		} catch (Exception e) {
			return "refuse";
		}
	}
	
	@RequestMapping("/login")
    public String login() {
        return "login";
    }
	
    @RequestMapping("/loginUser")
    public String loginUser(String username,String password,HttpSession session,Model model) {
        System.out.println("开始登陆认证……");
    	UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
        	//完成登录
        	subject.login(usernamePasswordToken);   
            User user=(User) subject.getPrincipal();
            session.setAttribute("user", user);
            System.err.println("登录成功");
            return "index";
        } catch(AuthenticationException e) {
        	e.printStackTrace();
        	model.addAttribute("msg", "认证失败");
            return "login";//返回登录页面
        } catch(Exception e) {
        	model.addAttribute("msg", "登录异常");
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
