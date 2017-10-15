package com.springboot.server.pictureserver.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.server.pictureserver.model.User;
import com.springboot.server.pictureserver.service.IUserService;

public class AuthRealm extends AuthorizingRealm {

	@Autowired
    private IUserService userService;
    
    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String username = utoken.getUsername();
        User user = userService.findUserByUserName(username);
        //放入shiro.调用CredentialsMatcher检验密码
        System.out.println(user.toString());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),this.getClass().getName());
        return info;
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
    	
        User user = (User) principal.fromRealm(this.getClass().getName()).iterator().next();
		// 根据 用户名到数据库中查询这个用户所拥有的所有的菜单权限
//        User user2 = userService.findUserByUserName(user.getUsername());
//		List<String> permissions = userService.queryMenusByUid(user2.getUserId());
        List<String> permissions = userService.queryMenusByUsername(user.getUsername());
        System.out.println(permissions.toString());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		if(permissions!=null && permissions.size()>0){
			info.addStringPermissions(permissions);
		}
		return info;
    }


}
