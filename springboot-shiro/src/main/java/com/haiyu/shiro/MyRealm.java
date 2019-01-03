package com.haiyu.shiro;

import java.util.List;

import com.haiyu.domain.User;
import com.haiyu.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author 林尤庆
 * @date 2018年3月23日 下午1:09:31
 * @version 1.0
 */
public class MyRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 得到当前用户
		Subject subject = SecurityUtils.getSubject();
		User dbUser = (User) subject.getPrincipal();
		List<String> perms = userService.findPermissionByUserId(dbUser.getId());
		if (perms != null) {
			for (String perm : perms) {
				if (!StringUtils.isEmpty(perm)) {
					info.addStringPermission(perm);
				}
			}
		}
		System.out.println(info.getStringPermissions());
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		// 1.获取用户输入的账户信息
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;
		/*
		 * 模拟数据库的密码 String name = "jack"; String password = "1234"; if
		 * (!token.getUsername().equals(name)) { // 用户不存在 return null; } User
		 * dbUser = new User(); dbUser.setName(name);
		 * dbUser.setPassword(password);
		 */

		User dbUser = userService.findByName(token.getUsername());
		if (dbUser == null) {
			// 用户不存在
			return null;
		}
		// 返回密码
		return new SimpleAuthenticationInfo(dbUser, dbUser.getPassword(), "");
	}

}
