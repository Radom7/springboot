package com.haiyu.controller;

import javax.servlet.http.HttpServletRequest;

import com.haiyu.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.util.StringUtils;

/**
 * @author 林尤庆
 * @date 2018年3月23日 下午1:24:07
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

	/**
	 * 登录
	 */
	@RequestMapping("/login")
	public String login(User user, String rememberMe, String code, HttpServletRequest request, Model model) {
		// 判断验证码是否正确
		if (!StringUtils.isEmpty(code)) {
			// 取出生成的验证码
			String verifyCode = (String) request.getSession().getAttribute("verifyCode");
			if (!code.equals(verifyCode)) {
				model.addAttribute("msg", "验证码输入有误");
				return "login";
			}
		}

		// 使用 shiro 进行登录
		Subject subject = SecurityUtils.getSubject();
		// AuthenticationToken token = new UsernamePasswordToken(user.getName(),
		// user.getPassword());

		// 使用 Shiro 对密码进行加密
		Md5Hash hash = new Md5Hash(user.getPassword(), user.getName(), 2);
		System.out.println("***********"+hash.toString());
		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), hash.toString());
		// 设置 remenmberMe 的功能
		if (rememberMe != null && rememberMe.equals("1")) {
			token.setRememberMe(true);
		}

		try {
			subject.login(token);
			// 登录成功
			User dbUser = (User) subject.getPrincipal();
			request.getSession().setAttribute("userName", dbUser.getName());
			return "redirect:/index";
		} catch (UnknownAccountException e) {
			model.addAttribute("msg", "用户名不存在");
			return "login";
		} catch (IncorrectCredentialsException e) {
			model.addAttribute("msg", "密码");
			return "login";
		}
	}

	/**
	 * 注销方法
	 */
	@RequestMapping("/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout(); // shiro底层删除session的会话信息
		return "redirect:/toLogin";
	}

}
